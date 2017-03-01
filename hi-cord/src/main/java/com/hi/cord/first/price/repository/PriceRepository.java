package com.hi.cord.first.price.repository;

import java.util.List;

import com.hi.cord.first.price.entity.Price;

public interface PriceRepository {

	Price findById(Long id);
	
	void insert(Price price);
	
	void deleteById(Long id);
	
	List<Price> findAllPrices(Price price);
	
}

