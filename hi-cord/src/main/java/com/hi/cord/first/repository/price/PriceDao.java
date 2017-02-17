package com.hi.cord.first.repository.price;

import java.util.List;

import com.hi.cord.common.model.Paging;
import com.hi.cord.first.entity.price.Price;

public interface PriceDao {

	Price findById(Long id);
	
	void save(Price price);
	
	void deleteById(Long id);
	
	int getCount(Paging paging);
	
	List<Price> findAllPrices(Paging paging);
	
}

