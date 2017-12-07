package com.denis.parserbackend.dao;

import java.util.List;

import com.denis.parserbackend.dto.DtoException;
import com.denis.parserbackend.dto.TypeInstrument;

public interface TypeInstrumentDAO {
	List<TypeInstrument> loadAllInfo() throws DtoException;

	TypeInstrument getById(int id) throws DtoException;

	TypeInstrument getByName(String name) throws DtoException;

	boolean add(TypeInstrument entity);

	boolean update(TypeInstrument entity);
}
