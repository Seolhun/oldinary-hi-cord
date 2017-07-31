package com.hi.cord.first.price.repository;

import java.util.List;

import com.hi.cord.first.price.entity.PriceRecord;

public interface PriceRecordRepository {
	
	void insert(PriceRecord priceRecord);

	PriceRecord findById(Long id);
	
	void deleteById(Long id);
	
	List<PriceRecord> findAllPriceRecords(PriceRecord priceRecord);
	
}