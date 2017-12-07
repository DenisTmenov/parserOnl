package com.denis.parserbackend.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.denis.parserbackend.dao.DAOException;
import com.denis.parserbackend.dao.TypeInstrumentDAO;
import com.denis.parserbackend.dto.DtoException;
import com.denis.parserbackend.dto.TypeInstrument;

@Repository("typeInstrumentDAO")
@Transactional
public class TypeInstrumentDAOImpl implements TypeInstrumentDAO {

	private final String EXCEPTION_PREFIX = "Problem in InstrumentDAOImpl in";
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Override
	public List<TypeInstrument> loadAllInfo() throws DtoException {
		return sessionFactory.getCurrentSession().createQuery("FROM TypeInstrument", TypeInstrument.class)
				.getResultList();
	}

	@Override
	public TypeInstrument getById(@NonNull int id) throws DtoException {
		try {
			return sessionFactory.getCurrentSession().get(TypeInstrument.class, Integer.valueOf(id));
		} catch (Exception ex) {
			throw new DAOException(EXCEPTION_PREFIX + " getById method.", ex);
		}
	}

	@Override
	public TypeInstrument getByName(@NonNull String name) {

		Query<?> query = sessionFactory.getCurrentSession()
				.createQuery("FROM TypeInstrument WHERE name = '" + name + "'");
		if (query != null) {
			return (TypeInstrument) query.getSingleResult();
		}

		return null;
	}

	@Override
	public boolean add(@NonNull TypeInstrument TypeInstrument) {
		try {
			sessionFactory.getCurrentSession().persist(TypeInstrument);

			return true;
		} catch (Exception ex) {
			throw new DAOException(EXCEPTION_PREFIX + " add method.", ex);
		}
	}

	@Override
	public boolean update(@NonNull TypeInstrument TypeInstrument) {
		try {
			sessionFactory.getCurrentSession().update(TypeInstrument);
			return true;
		}

		catch (Exception ex) {
			throw new DAOException(EXCEPTION_PREFIX + " update method.", ex);
		}
	}

}
