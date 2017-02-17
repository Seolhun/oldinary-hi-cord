package com.hi.cord.first.service.price;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hi.cord.common.model.Paging;
import com.hi.cord.first.entity.price.Price;

@Transactional(propagation=Propagation.REQUIRED, transactionManager="txManager", noRollbackFor={NullPointerException.class})
public interface PriceService {

	Price findById(Long id);

	void savePrice(Price price);

	void updatePrice(Price price);
	
	void deletePriceById(Long id);

	List<Price> findAllPrices(Paging paging);
	
	int getCount(Paging paging);
}