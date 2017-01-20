package com.brain.home.repository.price;

import java.util.List;

import com.brain.home.entity.common.Paging;
import com.brain.home.entity.price.Price;

public interface PriceDao {

	Price findById(Long id);
	
	void save(Price price);
	
	void deleteById(Long id);
	
	int getCount(Paging paging);
	
	List<Price> findAllPrices(Paging paging);
	
}

