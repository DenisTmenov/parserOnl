package com.denis.parserbackend.dao;

import java.util.List;

import com.denis.parserbackend.dto.DrillImage;

public interface DrillImageDAO {
	List<DrillImage> loadAllInfo();

	DrillImage getById(int id);

	boolean add(DrillImage entity);

	boolean update(DrillImage entity);
}
