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
import com.hi.cord.first.price.entity.PriceRecord;

@Repository("PriceRecordDao")
public class PriceRecordDaoImpl extends AbstractRepository<Integer, PriceRecord> implements PriceRecordDao {

	static final Logger logger = LoggerFactory.getLogger(PriceRecordDaoImpl.class);

	public PriceRecord findById(Long id) {
		PriceRecord priceRecord = getByKeyByLong(id);
		return priceRecord;
	}

	// Criteria는 무엇인가?
	@SuppressWarnings("unchecked")
	public List<PriceRecord> findAllPriceRecords(Paging paging) {
		int cPage = paging.getCPage();
		int sType = paging.getSType();
		String sText = paging.getSText();
		int limit = paging.getLimit();

		// 검색 로직
		Criteria criteria = createEntityCriteria().addOrder(Order.desc("id")).setFirstResult((cPage - 1) * limit)
				.setMaxResults(limit).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).add(Restrictions.eq("id", paging.getId()));
		List<PriceRecord> priceRecords = (List<PriceRecord>) criteria.list();
		return priceRecords;
	}

	public void save(PriceRecord priceRecord) {
		persist(priceRecord);
	}
	
	@Override
	public int getCount(Paging paging) {
		Long id=paging.getId();
		Query query = rawQuery("SELECT COUNT(*) FROM PRICE_RECORD WHERE PRICE_RECORD_PAID_BY_USER=" + id);
		return ((Number) query.uniqueResult()).intValue();
	}
	
	// Criteria는 무엇인가?
	public void deleteById(Long id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		PriceRecord priceRecord = (PriceRecord) crit.uniqueResult();
		delete(priceRecord);
	}
}
