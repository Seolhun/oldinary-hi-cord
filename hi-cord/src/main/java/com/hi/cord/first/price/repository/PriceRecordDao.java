package com.hi.cord.first.price.repository;

import java.util.List;

import com.hi.cord.common.model.Paging;
import com.hi.cord.first.price.entity.PriceRecord;

public interface PriceRecordDao {

	PriceRecord findById(Long id);
	
	void save(PriceRecord priceRecord);
	
	void deleteById(Long id);
	
	int getCount(Paging paging);
	
	List<PriceRecord> findAllPriceRecords(Paging paging);
	
}

