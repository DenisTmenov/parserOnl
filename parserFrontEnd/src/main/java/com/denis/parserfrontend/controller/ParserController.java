package com.denis.parserfrontend.controller;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.denis.parserbackend.service.HtmlParser;

@Controller
public class ParserController {

	@Autowired
	private HtmlParser htmlParser;

	@RequestMapping(value = "/parser", method = RequestMethod.GET)
	public ModelAndView parsePage() {

		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Parser");
		mv.addObject("userClickParser", true);

		return mv;
	}

	@RequestMapping(value = "/parser", method = RequestMethod.POST)
	public String parseNtImpactDrill(RedirectAttributes redirectAttributes,
			@RequestParam Map<String, String> allRequestParams) {

		// ModelAndView mv = new ModelAndView("page");

		for (Entry<String, String> iterable_element : allRequestParams.entrySet()) {
			// System.out.println(iterable_element.getKey() + " - " +
			// iterable_element.getValue());
			if (iterable_element.getKey().equals("cb_nt_impact_drill")
					&& iterable_element.getValue().equals("nt_impact_drill")) {
				htmlParser.setStart_URL(
						"https://remont.1k.by/instruments-drills/s.php?alias=instruments&alias2=drills&order=top&focuselementid=&selectedelementname=el_10741%5B%5D&viewmode=standart&keywords=&pricemin=&pricemax=&filter=actual&searchProducers=&el_10741%5B%5D=227615&el_9507%5B%5D=229768&from_el_9512=&to_el_9512=&from_el_9509=&to_el_9509=&from_el_9510=&to_el_9510=&el_9544=179509&from_el_9545=&to_el_9545=&from_el_9546=&to_el_9546=&from_el_9555=&to_el_9555=");
				try {
					htmlParser.start();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		}

		redirectAttributes.addAttribute("attributeRed", "123");
		return "redirect:/parser";

	}

}
