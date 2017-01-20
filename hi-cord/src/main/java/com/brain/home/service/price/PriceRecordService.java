package com.brain.home.service.price;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.brain.home.entity.common.Paging;
import com.brain.home.entity.price.PriceRecord;

@Transactional(propagation=Propagation.REQUIRED, transactionManager="txManager", noRollbackFor={NullPointerException.class})
public interface PriceRecordService {

	PriceRecord findById(Long id);

	void savePriceRecord(PriceRecord priceRecords);

	void updatePriceRecord(PriceRecord priceRecord);
	
	void deletePriceRecordById(Long id);

	List<PriceRecord> findAllPriceRecords(Paging paging);
	
	int getCount(Paging paging);
}