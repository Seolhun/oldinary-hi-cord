package com.hi.cord.first.service.price;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hi.cord.common.model.Paging;
import com.hi.cord.first.entity.price.PriceRecord;

@Transactional(propagation=Propagation.REQUIRED, transactionManager="txManager", noRollbackFor={NullPointerException.class})
public interface PriceRecordService {

	PriceRecord findById(Long id);

	void savePriceRecord(PriceRecord priceRecords);

	void updatePriceRecord(PriceRecord priceRecord);
	
	void deletePriceRecordById(Long id);

	List<PriceRecord> findAllPriceRecords(Paging paging);
	
	int getCount(Paging paging);
}