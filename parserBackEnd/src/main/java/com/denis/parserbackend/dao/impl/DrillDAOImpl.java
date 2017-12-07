package com.denis.parserbackend.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.denis.parserbackend.dao.DAOException;
import com.denis.parserbackend.dao.DrillDAO;
import com.denis.parserbackend.dto.Drill;
import com.denis.parserbackend.dto.DtoException;

@Repository("drillDAO")
@Transactional
public class DrillDAOImpl implements DrillDAO {

	private final String EXCEPTION_PREFIX = "Problem in InstrumentDAOImpl in";
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Override
	public List<Drill> loadAllInfo() throws DtoException {
		return sessionFactory.getCurrentSession().createQuery("FROM Drill", Drill.class).getResultList();
	}

	@Override
	public Drill getById(@NonNull int id) {
		try {
			return sessionFactory.getCurrentSession().get(Drill.class, Integer.valueOf(id));
		} catch (Exception ex) {
			throw new DAOException(EXCEPTION_PREFIX + " getById method.", ex);
		}
	}

	@Override
	public boolean add(@NonNull Drill drill) {
		try {
			sessionFactory.getCurrentSession().persist(drill);
		} catch (ConstraintViolationException e) {
			return false;
		}
		return true;

	}

	@Override
	public boolean update(@NonNull Drill drill) {

		sessionFactory.getCurrentSession().update(drill);
		return true;

	}

	@Override
	public Drill getByURL(@NonNull String url) {
		Query<?> query = sessionFactory.getCurrentSession().createQuery("FROM Drill WHERE url = '" + url + "'");
		if (query != null) {

			List<?> resultList = query.getResultList();

			return (Drill) resultList.get(0);
		} else {

			return null;
		}
	}

}
