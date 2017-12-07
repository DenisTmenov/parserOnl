package com.denis.parserbackend.service;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.denis.parserbackend.dao.DrillDAO;
import com.denis.parserbackend.dao.DrillImageDAO;
import com.denis.parserbackend.dto.Drill;
import com.denis.parserbackend.dto.DrillImage;
import com.denis.parserbackend.utils.StringUtils;

@Service
public class HtmlParser {

	private final String ERROR = "(HtmlParser) Problem ";

	private String start_URL = "";

	private Map<Integer, Document> urlDocMap = new HashMap<>();

	private Map<String, List<byte[]>> imageMap = new HashMap<>();

	@Autowired
	private EntityCreator entityCreator;

	@Autowired
	private DrillDAO drillDAO;

	@Autowired
	private DrillImageDAO drillImageDAO;

	public String getStart_URL() {
		return start_URL;
	}

	public void setStart_URL(String start_URL) {
		this.start_URL = start_URL;
	}

	public void start() throws IOException {

		saveDrillOnDB(getInfoFromPages());
	}

	private void saveDrillOnDB(List<Map<String, String>> list) {

		for (Map<String, String> map : list) {
			Drill createDrill = null;
			try {
				createDrill = entityCreator.createDrill(map);
				drillDAO.add(createDrill);

				List<byte[]> images = imageMap.get(createDrill.getUrl());

				////////////
				String urlDrill = createDrill.getUrl();
				Drill drill2 = drillDAO.getByURL(urlDrill);

				int idDrill = drill2.getId();

				for (byte[] bs : images) {

					DrillImage drillImage = entityCreator.createDrillImage(idDrill, bs);

					drillImageDAO.add(drillImage);
				}

				byte[] bs = drillImageDAO.getById(1).getImg();

				Files.write(Paths.get("D://img.jpeg"), bs, StandardOpenOption.CREATE);

			} catch (Exception e) {

				String message = e.getMessage();
				Throwable cause = e.getCause();
				String localizedMessage = e.getLocalizedMessage();
				Throwable[] suppressed = e.getSuppressed();

				System.out.println("Problem with createDrill!!!");
			}

		}

	}

	private List<Map<String, String>> getInfoFromPages() throws IOException {

		List<Map<String, String>> result = new ArrayList<>();

		List<String> instrumentURLs = getInstrumentURLs();
		if (instrumentURLs.size() > 0) {

			Document doc = null;

			for (String instrumentURL : instrumentURLs) {

				doc = Jsoup.connect(instrumentURL).timeout(10000).get();

				Map<String, String> instrumentInfo = getInstrumentInfoFromPage(doc, instrumentURL);

				result.add(instrumentInfo);
			}
		}

		return result;

	}

	private List<String> getInstrumentURLs() {
		Document doc = null;
		List<String> result = new ArrayList<>();

		for (int i = 1; i <= getCountPages(); i++) {
			if (urlDocMap.containsKey(i)) {
				doc = urlDocMap.get(i);
			} else {
				try {
					doc = Jsoup.connect(start_URL + "&page=" + i).get();
				} catch (IOException e) {
					System.out.println("Problem with get connection by url!!!");
					return result;
				}
				urlDocMap.put(i, doc); // add Document (all information) about URL
			}

			if (doc != null) {
				Elements elementsByClass = doc.getElementsByClass("pr-line_link");
				for (Element element : elementsByClass) {
					result.add(element.select("a").first().attr("abs:href"));
				}

			}

		}

		result.clear();
		result.add("https://remont.1k.by/instruments-drills/makita/Makita_HP1640-400583.html");

		return result;
	}

	private int getCountPages() {

		int lastNumber = 0;
		boolean detector = true;
		Document doc = null;
		do {
			try {
				if (lastNumber == 0) {
					doc = Jsoup.connect(start_URL).get();
					urlDocMap.put(1, doc); // add Document (all information) about URL
				} else {
					doc = Jsoup.connect(start_URL + "&page=" + lastNumber).get();
					urlDocMap.put(lastNumber, doc);
				}

			} catch (IOException e) {

				throw new ServiceException(ERROR + "while getting connection! (getCountPages)", e);
			}

			Elements allElements = doc.getElementsByClass("paging_page");
			int docNumber = 0;

			if (allElements != null) {
				int i = 0;
				if (allElements.size() > 2) {
					i = allElements.size() - 2;
				}
				for (; i < allElements.size(); i++) {

					if (StringUtils.isNumeric(allElements.get(i).text())) {
						docNumber = Integer.valueOf(allElements.get(i).text());
					}
				}
			}

			if (docNumber == lastNumber) {
				detector = false;
			} else {
				lastNumber = docNumber;
			}

		} while (detector);

		if (lastNumber == 0) {
			lastNumber++;
		}

		return lastNumber;
	}

	private Map<String, String> getInstrumentInfoFromPage(Document doc, String instrumentURL) {

		Map<String, String> result = new HashMap<>();
		result.put("url", instrumentURL);
		result.put("type", "Безударная дрель");
		result.put("type_modes_of_operation", "Cверление");
		result.put("power_supply", "Сеть 220V");

		result = getInfoFromAreaBCrumbs(doc, result);

		result = getInfoFromAriaBPrTech(doc, result);

		imageMap.put(instrumentURL, getImages(doc));

		return result;
	}

	private Map<String, String> getInfoFromAreaBCrumbs(Document doc, Map<String, String> result) {

		Elements elementsBCrumbs = doc.getElementsByClass("b-crumbs");
		if (!elementsBCrumbs.isEmpty()) {
			Elements elementsSpan = elementsBCrumbs.first().getElementsByTag("span");
			for (Element elementSpan : elementsSpan) {

				Elements elementsMeta = elementSpan.getElementsByTag("meta");
				if (!elementsMeta.isEmpty()) {
					String attr = elementsMeta.first().attr("content");
					if (attr.equals("2")) {
						Elements elementsSpan2 = elementSpan.getElementsByTag("span");
						if (!elementsSpan2.isEmpty()) {
							result.put("brand", elementsSpan2.first().text());
						}
					}
					if (attr.equals("3")) {
						Elements elementsSpan3 = elementSpan.getElementsByTag("span");
						if (!elementsSpan3.isEmpty()) {
							result.put("model_name",
									elementsSpan3.first().text().replaceAll(result.get("brand"), "").trim());
						}
					}
				}

			}
		}

		return result;
	}

	private Map<String, String> getInfoFromAriaBPrTech(Document doc, Map<String, String> result) {

		Elements tables = doc.getElementsByClass("b-pr-tech");
		for (Element tablesElement : tables) {

			Elements tbodies = tablesElement.getElementsByTag("tbody");
			for (Element tbodiesElement : tbodies) {

				Elements thTag = tbodiesElement.getElementsByTag("th");
				Elements tdTag = tbodiesElement.getElementsByTag("td");
				for (int i = 0; i < thTag.size() && i < tdTag.size(); i++) {
					if (thTag.get(i).text().equals("Потребляемая мощность")) {
						result.put("power", tdTag.get(i).text());
					} else if (thTag.get(i).text().equals("Макс. число оборотов холостого хода")) {
						result.put("rotational_speed", tdTag.get(i).text());
					} else if (thTag.get(i).text().equals("Макс. крутящий момент")) {
						result.put("torque", tdTag.get(i).text());
					} else if (thTag.get(i).text().equals("Количество скоростей работы ")) {
						result.put("rotational_speed_modes", tdTag.get(i).text());
					} else if (thTag.get(i).text().equals("Потребляемая мощность")) {
						result.put("power", tdTag.get(i).text());
					} else if (thTag.get(i).text().equals("Макс. число оборотов холостого хода")) {
						result.put("rotational_speed", tdTag.get(i).text());
					} else if (thTag.get(i).text().equals("Макс. крутящий момент")) {
						result.put("torque", tdTag.get(i).text());
					} else if (thTag.get(i).text().equals("Тип патрона")) {
						result.put("cartridge", tdTag.get(i).text());
					} else if (thTag.get(i).text().equals("Диаметр патрона, макс")) {
						result.put("diameter_of_cartridge", tdTag.get(i).text());
					} else if (thTag.get(i).text().equals("Вес")) {
						result.put("weight", tdTag.get(i).text());
					} else if (thTag.get(i).text().equals("Дерево")) {
						result.put("maximum_drilling_diameter_wood", tdTag.get(i).text());
					} else if (thTag.get(i).text().equals("Металл")) {
						result.put("maximum_drilling_diameter_metal", tdTag.get(i).text());
					}

				}

			}
		}
		return result;
	}

	private List<byte[]> getImages(Document doc) {

		List<byte[]> result = new ArrayList<>();

		Elements elementsProductThumb = doc.getElementsByClass("product_thumb");
		for (Element elementProductThumb : elementsProductThumb) {
			if (!elementProductThumb.attr("data-srcbig").isEmpty()) {
				result.add(getImage(elementProductThumb.attr("data-srcbig")));
			}
		}

		return result;
	}

	private byte[] getImage(String imgURL) {

		URL url = null;
		try {
			url = new URL(imgURL);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		InputStream in = null;
		try {
			in = new BufferedInputStream(url.openStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		int n = 0;
		try {
			while (-1 != (n = in.read(buf))) {
				out.write(buf, 0, n);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] response = out.toByteArray();

		return response;

	}

}
