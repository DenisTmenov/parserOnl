package com.denis.parserbackend.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.denis.parserbackend.dao.DAOException;
import com.denis.parserbackend.dao.TypeCartridgeDrillDAO;
import com.denis.parserbackend.dto.DtoException;
import com.denis.parserbackend.dto.TypeCartridgeDrill;

@Repository("typeCartridgeDrillDAO")
@Transactional
public class TypeCartridgeDrillDAOImpl implements TypeCartridgeDrillDAO {

	private final String EXCEPTION_PREFIX = "Problem in InstrumentDAOImpl in";
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Override
	public List<TypeCartridgeDrill> loadAllInfo() throws DtoException {
		return sessionFactory.getCurrentSession().createQuery("FROM TypeCartridgeDrill", TypeCartridgeDrill.class)
				.getResultList();
	}

	@Override
	public TypeCartridgeDrill getById(@NonNull int id) throws DtoException {
		try {
			return sessionFactory.getCurrentSession().get(TypeCartridgeDrill.class, Integer.valueOf(id));
		} catch (Exception ex) {
			throw new DAOException(EXCEPTION_PREFIX + " getById method.", ex);
		}
	}

	@Override
	public TypeCartridgeDrill getByName(@NonNull String name) {

		Query<?> query = sessionFactory.getCurrentSession()
				.createQuery("FROM TypeCartridgeDrill WHERE name = '" + name + "'");
		if (query != null) {
			return (TypeCartridgeDrill) query.getSingleResult();
		}

		return null;

	}

	@Override
	public boolean add(@NonNull TypeCartridgeDrill TypeCartridgeDrill) {
		try {
			sessionFactory.getCurrentSession().persist(TypeCartridgeDrill);

			return true;
		} catch (Exception ex) {
			throw new DAOException(EXCEPTION_PREFIX + " add method.", ex);
		}
	}

	@Override
	public boolean update(@NonNull TypeCartridgeDrill TypeCartridgeDrill) {
		try {
			sessionFactory.getCurrentSession().update(TypeCartridgeDrill);
			return true;
		}

		catch (Exception ex) {
			throw new DAOException(EXCEPTION_PREFIX + " update method.", ex);
		}
	}

}
