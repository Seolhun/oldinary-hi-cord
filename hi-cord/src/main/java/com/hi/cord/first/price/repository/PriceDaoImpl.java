package com.hi.cord.first.price.repository;

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
import com.hi.cord.first.price.entity.Price;

@Repository("PriceDao")
public class PriceDaoImpl extends AbstractRepository<Integer, Price> implements PriceDao {

	static final Logger logger = LoggerFactory.getLogger(PriceDaoImpl.class);

	public Price findById(Long id) {
		Price Price = getByKeyByLong(id);
		return Price;
	}

	// Criteria는 무엇인가?
	@SuppressWarnings("unchecked")
	public List<Price> findAllPrices(Paging paging) {
		int cPage = paging.getCPage();
		int sType = paging.getSType();
		String sText = paging.getSText();
		int limit = paging.getLimit();

		// 검색 로직
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"))
				.setFirstResult((cPage - 1) * limit)
				.setMaxResults(limit)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		criteria.add(Restrictions.eq("delCheck", 0));
		List<Price> Prices = (List<Price>) criteria.list();
		return Prices;
	}

	public void save(Price Price) {
		persist(Price);
	}
	
	@Override
	public int getCount(Paging paging) {
		String condition = "";
		if (paging.getTableName() != null) {
			condition = "WHERE state='" + paging.getTableName() + "'";
		}
		Query query = rawQuery("SELECT COUNT(*) FROM PRICE " + condition);
		return ((Number) query.uniqueResult()).intValue();
	}
	
	// Criteria는 무엇인가?
	public void deleteById(Long id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		Price Price = (Price) crit.uniqueResult();
		delete(Price);
	}
}
