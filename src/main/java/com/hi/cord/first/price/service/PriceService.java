package com.hi.cord.first.price.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hi.cord.first.price.entity.Price;

@Transactional(propagation=Propagation.REQUIRED, transactionManager="txManager", noRollbackFor={NullPointerException.class})
public interface PriceService {

	Price findById(Long id);

	void savePrice(Price price);

	void updatePrice(Price price);
	
	void deletePriceById(Long id);

	List<Price> findAllPrices(Price price);
}