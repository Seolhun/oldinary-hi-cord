package com.hi.cord.first.repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDao<PK extends Serializable, T> {

	private final Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public AbstractDao() {
		this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	}

	@Autowired
	private SessionFactory sessionFactory;
	
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public T getByKey(PK id) {
		return (T) getSession().get(persistentClass, id);
	}
	
	public T getByKeyByLong(Long id) {
		return (T)getSession().get(persistentClass, id);
	}

	public void persist(T entity) {
		getSession().persist(entity);
	}

	public void update(T entity) {
		getSession().update(entity);
	}
	
	public void delete(T entity) {
		getSession().delete(entity);
	}

	public Query rawQuery(String raw) {
		Query query=getSession().createSQLQuery(raw);
		return query;
	}
	
	public int count(){
		getSession().createCriteria("Book").setProjection(Projections.rowCount()).uniqueResult();
		return 0;
	}

	protected Criteria createEntityCriteria() {
		return getSession().createCriteria(persistentClass);
	}
	
	
}
