package com.denis.parserbackend.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.denis.parserbackend.dao.BrandDAO;
import com.denis.parserbackend.dao.DAOException;
import com.denis.parserbackend.dto.Brand;
import com.denis.parserbackend.dto.DtoException;

@Repository("brandDAO")
@Transactional
public class BrandDAOImpl implements BrandDAO {

	private final String EXCEPTION_PREFIX = "Problem in BrandDAOImpl in";
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Override
	public List<Brand> loadAllInfo() throws DtoException {
		try {
			return sessionFactory.getCurrentSession().createQuery("FROM Brand", Brand.class).getResultList();
		} catch (Exception ex) {
			throw new DAOException(EXCEPTION_PREFIX + " loadAllInfo method.", ex);
		}
	}

	@Override
	public Brand getById(@NonNull int id) throws DtoException {
		try {
			return sessionFactory.getCurrentSession().get(Brand.class, Integer.valueOf(id));
		} catch (Exception ex) {
			throw new DAOException(EXCEPTION_PREFIX + " getById method.", ex);
		}
	}

	@Override
	public Brand getByName(@NonNull String name) {

		Query<?> query = sessionFactory.getCurrentSession().createQuery("FROM Brand WHERE name = '" + name + "'");
		if (query != null) {
			return (Brand) query.getSingleResult();
		} else {
			Brand brand = new Brand();
			brand.setName(name);
			add(brand);
			return (Brand) sessionFactory.getCurrentSession().createQuery("FROM Brand WHERE name = '" + name + "'")
					.getSingleResult();
		}
	}

	@Override
	public boolean add(@NonNull Brand Brand) {
		try {
			sessionFactory.getCurrentSession().persist(Brand);
			return true;
		} catch (Exception ex) {
			throw new DAOException(EXCEPTION_PREFIX + " add method.", ex);
		}
	}

	@Override
	public boolean update(@NonNull Brand Brand) {
		try {
			sessionFactory.getCurrentSession().update(Brand);
			return true;
		} catch (Exception ex) {
			throw new DAOException(EXCEPTION_PREFIX + " update method.", ex);
		}
	}

}
