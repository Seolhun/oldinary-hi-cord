package com.brain.home.repository.price;

import java.util.List;

import com.brain.home.entity.common.Paging;
import com.brain.home.entity.price.PriceRecord;

public interface PriceRecordDao {

	PriceRecord findById(Long id);
	
	void save(PriceRecord priceRecord);
	
	void deleteById(Long id);
	
	int getCount(Paging paging);
	
	List<PriceRecord> findAllPriceRecords(Paging paging);
	
}

