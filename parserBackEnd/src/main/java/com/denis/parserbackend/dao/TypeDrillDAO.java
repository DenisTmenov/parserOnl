package com.denis.parserbackend.dao;

import java.util.List;

import com.denis.parserbackend.dto.DtoException;
import com.denis.parserbackend.dto.TypeDrill;

public interface TypeDrillDAO {
	List<TypeDrill> loadAllInfo() throws DtoException;

	TypeDrill getById(int id) throws DtoException;

	TypeDrill getByName(String name);

	boolean add(TypeDrill entity);

	boolean update(TypeDrill entity);
}
