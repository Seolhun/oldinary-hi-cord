package com.hi.cord.first.price.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hi.cord.common.repository.AbstractRepository;
import com.hi.cord.first.price.entity.PriceRecord;

@Repository("PriceRecordRepository")
public class PriceRecordRepositoryImpl extends AbstractRepository<Integer, PriceRecord> implements PriceRecordRepository {

	static final Logger logger = LoggerFactory.getLogger(PriceRecordRepositoryImpl.class);

	public PriceRecord findById(Long id) {
		PriceRecord priceRecord = getByKeyByLong(id);
		if (priceRecord != null) {
			Hibernate.initialize(priceRecord.getPriceRecorPaidByUser());
		}
		return priceRecord;
	}

	// Criteria는 무엇인가?
	@SuppressWarnings("unchecked")
	public List<PriceRecord> findAllPriceRecords(PriceRecord priceRecord) {
		// 검색 로직
		Criteria criteria = createEntityCriteria().addOrder(Order.desc("priceRecordId"))
			.add(Restrictions.eq("priceRecordDelCheck", 0))	
			.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<PriceRecord> priceRecords =  criteria.list();
		return priceRecords;
	}

	public void insert(PriceRecord priceRecord) {
		persist(priceRecord);
	}
	
	// Criteria는 무엇인가?
	public void deleteById(Long id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("priceRecordId", id));
		PriceRecord priceRecord = (PriceRecord) crit.uniqueResult();
		delete(priceRecord);
	}
}
