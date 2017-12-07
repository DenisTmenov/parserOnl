package com.denis.parserbackend.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.denis.parserbackend.dao.DAOException;
import com.denis.parserbackend.dao.TypeModesOfOperationDrillDAO;
import com.denis.parserbackend.dto.DtoException;
import com.denis.parserbackend.dto.TypeModesOfOperationDrill;

@Repository("typeModesOfOperationDrillDAO")
@Transactional
public class TypeModesOfOperationDrillDAOImpl implements TypeModesOfOperationDrillDAO {

	private final String EXCEPTION_PREFIX = "Problem in InstrumentDAOImpl in";
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Override
	public List<TypeModesOfOperationDrill> loadAllInfo() throws DtoException {
		return sessionFactory.getCurrentSession()
				.createQuery("FROM TypeModesOfOperationDrill", TypeModesOfOperationDrill.class).getResultList();
	}

	@Override
	public TypeModesOfOperationDrill getById(@NonNull int id) throws DtoException {
		try {
			return sessionFactory.getCurrentSession().get(TypeModesOfOperationDrill.class, Integer.valueOf(id));
		} catch (Exception ex) {
			throw new DAOException(EXCEPTION_PREFIX + " getById method.", ex);
		}
	}

	@Override
	public TypeModesOfOperationDrill getByName(@NonNull String name) throws DtoException {

		Query<?> query = sessionFactory.getCurrentSession()
				.createQuery("FROM TypeModesOfOperationDrill WHERE name = '" + name + "'");
		if (query != null) {
			return (TypeModesOfOperationDrill) query.getSingleResult();
		}

		return null;

	}

	@Override
	public boolean add(@NonNull TypeModesOfOperationDrill TypeModesOfOperationDrill) {
		try {
			sessionFactory.getCurrentSession().persist(TypeModesOfOperationDrill);

			return true;
		} catch (Exception ex) {
			throw new DAOException(EXCEPTION_PREFIX + " add method.", ex);
		}
	}

	@Override
	public boolean update(@NonNull TypeModesOfOperationDrill TypeModesOfOperationDrill) {
		try {
			sessionFactory.getCurrentSession().update(TypeModesOfOperationDrill);
			return true;
		}

		catch (Exception ex) {
			throw new DAOException(EXCEPTION_PREFIX + " update method.", ex);
		}
	}

}
