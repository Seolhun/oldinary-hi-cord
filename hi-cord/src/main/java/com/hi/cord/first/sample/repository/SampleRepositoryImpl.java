package com.hi.cord.first.sample.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hi.cord.common.repository.AbstractRepository;
import com.hi.cord.first.stadium.model.Stadium;

@Repository("sampleRepository")
public class SampleRepositoryImpl extends AbstractRepository<Long, Stadium> implements SampleRepository {
	static final Logger log = LoggerFactory.getLogger(SampleRepositoryImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<Stadium> findAll(Stadium stadium) {
		log.info("TEST : findAll"+stadium.toString());
		Criteria criteria = createEntityCriteria().addOrder(Order.desc("stadiumId")).add(Restrictions.eq("stadiumDelCheck", 0))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Stadium> stadiums =criteria.list();
		log.info("Parameter : "+stadiums.toString());
		return stadiums;
	}
	
	@Override
	public Stadium findById(Long id) {
		Stadium stadium = getByKey(id);
		return stadium;
	}

	@Override
	public void insert(Stadium stadium) {
		persist(stadium);
	}

	@Override
	public boolean delete(Long id) {
		return delete(id);
	}
}
