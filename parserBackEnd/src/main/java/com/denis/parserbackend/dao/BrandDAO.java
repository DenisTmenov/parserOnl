package com.denis.parserbackend.dao;

import java.util.List;

import com.denis.parserbackend.dto.Brand;
import com.denis.parserbackend.dto.DtoException;

public interface BrandDAO {
	List<Brand> loadAllInfo() throws DtoException;

	Brand getById(int id) throws DtoException;

	Brand getByName(String name) throws DtoException;

	boolean add(Brand entity);

	boolean update(Brand entity);
}
