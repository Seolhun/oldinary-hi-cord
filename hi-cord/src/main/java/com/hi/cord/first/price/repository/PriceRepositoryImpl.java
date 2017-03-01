package com.hi.cord.first.price.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hi.cord.common.repository.AbstractRepository;
import com.hi.cord.first.price.entity.Price;

@Repository("PriceRepository")
public class PriceRepositoryImpl extends AbstractRepository<Integer, Price> implements PriceRepository {
	static final Logger log = LoggerFactory.getLogger(PriceRepositoryImpl.class);

	public Price findById(Long id) {
		Price Price = getByKeyByLong(id);
		return Price;
	}

	// Criteria는 무엇인가?
	@SuppressWarnings("unchecked")
	public List<Price> findAllPrices(Price price) {
		// 검색 로직
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("priceId"))
		.add(Restrictions.eq("priceDelCheck", 0))
		.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Price> Prices = criteria.list();
		return Prices;
	}

	public void insert(Price Price) {
		persist(Price);
	}
	
	// Criteria는 무엇인가?
	public void deleteById(Long id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("priceId", id));
		Price Price = (Price) crit.uniqueResult();
		delete(Price);
	}
}
