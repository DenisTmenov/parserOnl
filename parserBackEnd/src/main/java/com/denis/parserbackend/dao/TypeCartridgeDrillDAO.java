package com.denis.parserbackend.dao;

import java.util.List;

import com.denis.parserbackend.dto.DtoException;
import com.denis.parserbackend.dto.TypeCartridgeDrill;

public interface TypeCartridgeDrillDAO {
	List<TypeCartridgeDrill> loadAllInfo() throws DtoException;

	TypeCartridgeDrill getById(int id) throws DtoException;

	TypeCartridgeDrill getByName(String name) throws DtoException;

	boolean add(TypeCartridgeDrill entity);

	boolean update(TypeCartridgeDrill entity);
}
