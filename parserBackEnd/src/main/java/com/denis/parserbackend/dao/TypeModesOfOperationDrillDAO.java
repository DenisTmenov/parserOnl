package com.denis.parserbackend.dao;

import java.util.List;

import com.denis.parserbackend.dto.DtoException;
import com.denis.parserbackend.dto.TypeModesOfOperationDrill;

public interface TypeModesOfOperationDrillDAO {
	List<TypeModesOfOperationDrill> loadAllInfo() throws DtoException;

	TypeModesOfOperationDrill getById(int id) throws DtoException;

	TypeModesOfOperationDrill getByName(String name) throws DtoException;

	boolean add(TypeModesOfOperationDrill entity);

	boolean update(TypeModesOfOperationDrill entity);
}
