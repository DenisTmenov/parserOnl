package com.denis.parserbackend.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.denis.parserbackend.dao.DAOException;
import com.denis.parserbackend.dao.DrillImageDAO;
import com.denis.parserbackend.dto.DrillImage;
import com.denis.parserbackend.dto.DtoException;

@Repository("drillImageDAO")
@Transactional
public class DrillImageDAOImpl implements DrillImageDAO {

	private final String EXCEPTION_PREFIX = "Problem in DrillImageDAOImpl in";
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Override
	public List<DrillImage> loadAllInfo() throws DtoException {
		try {
			return sessionFactory.getCurrentSession().createQuery("FROM DrillImage", DrillImage.class).getResultList();
		} catch (Exception ex) {
			throw new DAOException(EXCEPTION_PREFIX + " loadAllInfo method.", ex);
		}
	}

	@Override
	public DrillImage getById(@NonNull int id) throws DtoException {
		try {
			return sessionFactory.getCurrentSession().get(DrillImage.class, Integer.valueOf(id));
		} catch (Exception ex) {
			throw new DAOException(EXCEPTION_PREFIX + " getById method.", ex);
		}
	}

	@Override
	public boolean add(@NonNull DrillImage DrillImage) {
		try {
			sessionFactory.getCurrentSession().persist(DrillImage);
			return true;
		} catch (Exception ex) {
			throw new DAOException(EXCEPTION_PREFIX + " add method.", ex);
		}
	}

	@Override
	public boolean update(@NonNull DrillImage DrillImage) {
		try {
			sessionFactory.getCurrentSession().update(DrillImage);
			return true;
		} catch (Exception ex) {
			throw new DAOException(EXCEPTION_PREFIX + " update method.", ex);
		}
	}

}
