package com.denis.parserbackend.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.denis.parserbackend.dao.BrandDAO;
import com.denis.parserbackend.dao.TypeCartridgeDrillDAO;
import com.denis.parserbackend.dao.TypeDrillDAO;
import com.denis.parserbackend.dao.TypeInstrumentDAO;
import com.denis.parserbackend.dao.TypeModesOfOperationDrillDAO;
import com.denis.parserbackend.dto.Drill;
import com.denis.parserbackend.dto.DrillImage;
import com.denis.parserbackend.dto.DtoException;

@Controller
public class EntityCreator {

	@Autowired
	private BrandDAO brandDAO;
	@Autowired
	private TypeCartridgeDrillDAO typeCartridgeDrillDAO;
	@Autowired
	private TypeDrillDAO typeDrillDAO;
	@Autowired
	private TypeInstrumentDAO typeInstrumentDAO;
	@Autowired
	private TypeModesOfOperationDrillDAO typeModesOfOperationDrillDAO;

	private final String EXCEPTION_PREFIX = "Problem in EntityCreator in";

	public Drill createDrill(Map<String, String> info) throws Exception {
		Drill result = new Drill();

		result.setModel_name(info.get("model_name"));
		result.setPower(info.get("power"));
		result.setRotational_speed(info.get("rotational_speed"));
		result.setTorque(info.get("torque"));
		result.setDiameter_of_cartridge(info.get("diameter_of_cartridge"));
		result.setMaximum_drilling_diameter_metal(info.get("maximum_drilling_diameter_metal")); //
		result.setMaximum_drilling_diameter_wood(info.get("maximum_drilling_diameter_wood")); //
		result.setWeight(info.get("weight"));
		result.setPrice(0);
		result.setUrl(info.get("url"));

		if (info.containsKey("type_modes_of_operation")) {
			try {
				result.setType_modes_of_operation_id(
						typeModesOfOperationDrillDAO.getByName(info.get("type_modes_of_operation")).getId());

			} catch (DtoException e) {
				throw new ServiceException(EXCEPTION_PREFIX + " modes_of_operation_id.", e);
			}
		}

		if (info.containsKey("cartridge")) {
			if (info.get("cartridge").equals("ключевой")) {
				info.put("cartridge", "Кулачковый (под ключ)");
			}

			try {
				if (info.containsKey("cartridge")) {
					result.setCartridge_id(typeCartridgeDrillDAO.getByName(info.get("cartridge")).getId());
				}
			} catch (DtoException e) {
				throw new ServiceException(EXCEPTION_PREFIX + " cartridge_id.", e);
			}
		}

		if (info.containsKey("type")) {
			try {
				result.setType_id(typeDrillDAO.getByName(info.get("type")).getId());

			} catch (DtoException e) {
				throw new ServiceException(EXCEPTION_PREFIX + " type_id.", e);
			}
		}

		if (info.containsKey("brand")) {
			if (info.get("brand").equals("STERN")) {
				info.put("brand", "STERN Austria");
			}
			try {
				result.setBrand_id(brandDAO.getByName(info.get("brand").replaceAll("!", "")).getId());

			} catch (DtoException e) {
				throw new ServiceException(EXCEPTION_PREFIX + " brand_id.", e);
			}
		}

		if (info.containsKey("power_supply")) {

			try {
				result.setPower_supply_id(typeInstrumentDAO.getByName(info.get("power_supply")).getId());

			} catch (DtoException e) {
				throw new ServiceException(EXCEPTION_PREFIX + " power_supply_id.", e);
			}
		}

		return result;
	}

	public DrillImage createDrillImage(int idDrill, byte[] bs) {

		DrillImage result = new DrillImage();

		result.setImg(bs);
		result.setId_product(idDrill);

		return result;
	}

}
