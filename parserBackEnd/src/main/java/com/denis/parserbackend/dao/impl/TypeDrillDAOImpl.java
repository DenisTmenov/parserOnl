package com.denis.parserbackend.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.denis.parserbackend.dao.DAOException;
import com.denis.parserbackend.dao.TypeDrillDAO;
import com.denis.parserbackend.dto.DtoException;
import com.denis.parserbackend.dto.TypeDrill;

@Repository("typeDrillDAO")
@Transactional
public class TypeDrillDAOImpl implements TypeDrillDAO {

	private final String EXCEPTION_PREFIX = "Problem in TypeDrillDAOImpl in";
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Override
	public List<TypeDrill> loadAllInfo() throws DtoException {
		try {
			return sessionFactory.getCurrentSession().createQuery("FROM TypeDrill", TypeDrill.class).getResultList();
		} catch (Exception ex) {
			throw new DAOException(EXCEPTION_PREFIX + " loadAllInfo method.", ex);
		}
	}

	@Override
	public TypeDrill getById(@NonNull int id) throws DtoException {
		try {
			return sessionFactory.getCurrentSession().get(TypeDrill.class, Integer.valueOf(id));
		} catch (Exception ex) {
			throw new DAOException(EXCEPTION_PREFIX + " getById method.", ex);
		}
	}

	@Override
	public TypeDrill getByName(@NonNull String name) {

		Query<?> query = sessionFactory.getCurrentSession().createQuery("FROM TypeDrill WHERE name = '" + name + "'");
		if (query != null) {
			return (TypeDrill) query.getSingleResult();
		}

		return null;

	}

	@Override
	public boolean add(@NonNull TypeDrill TypeDrill) {
		try {
			sessionFactory.getCurrentSession().persist(TypeDrill);
			return true;
		} catch (Exception ex) {
			throw new DAOException(EXCEPTION_PREFIX + " add method.", ex);
		}
	}

	@Override
	public boolean update(@NonNull TypeDrill TypeDrill) {
		try {
			sessionFactory.getCurrentSession().update(TypeDrill);
			return true;
		}

		catch (Exception ex) {
			throw new DAOException(EXCEPTION_PREFIX + " update method.", ex);
		}
	}

}
