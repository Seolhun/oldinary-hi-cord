package com.hi.cord.first.stadium.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hi.cord.common.model.Paging;
import com.hi.cord.common.repository.AbstractRepository;
import com.hi.cord.first.stadium.model.Stadium;

@Repository("stadiumDao")
public class StadiumDAOImpl extends AbstractRepository<Long, Stadium> implements StadiumDAO {
	static final Logger log = LoggerFactory.getLogger(StadiumDAOImpl.class);

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
	public void save(Stadium stadium) {
		persist(stadium);
	}

	@Override
	public void delete(Long id) {
		delete(id);
	}

	
	@Override
	public int getCount(Paging paging) {
		String condition = "";
		if (paging.getTableName() != null) {
			condition = "BOARD WHERE BOARD_TYPE='" + paging.getTableName() + "'";
		}
		Query query = rawQuery("SELECT COUNT(*) FROM " + condition);
		return ((Number) query.uniqueResult()).intValue();
	}
//	@Override
//	public int getCount2(Paging paging) {
//		// 검색 로직
//		Criteria criteria = createEntityCriteria().addOrder(Order.desc("id")).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//		int count=((Integer)criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
//		return count;
//	}
}
