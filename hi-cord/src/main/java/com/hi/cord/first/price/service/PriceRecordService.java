package com.hi.cord.first.price.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hi.cord.first.price.entity.PriceRecord;

@Transactional(propagation=Propagation.REQUIRED, transactionManager="txManager", noRollbackFor={NullPointerException.class})
public interface PriceRecordService {

	PriceRecord findById(Long id);

	void savePriceRecord(PriceRecord priceRecords);

	void updatePriceRecord(PriceRecord priceRecord);
	
	void deletePriceRecordById(Long id);

	List<PriceRecord> findAllPriceRecords(PriceRecord priceRecord);
}