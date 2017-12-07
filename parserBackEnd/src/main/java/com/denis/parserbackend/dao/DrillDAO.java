package com.denis.parserbackend.dao;

import java.util.List;

import com.denis.parserbackend.dto.Drill;
import com.denis.parserbackend.dto.DtoException;

public interface DrillDAO {
	List<Drill> loadAllInfo() throws DtoException;

	Drill getById(int id);

	Drill getByURL(String url);

	boolean add(Drill entity);

	boolean update(Drill entity);
}
