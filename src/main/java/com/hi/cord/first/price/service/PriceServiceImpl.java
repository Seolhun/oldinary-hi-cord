package com.hi.cord.first.price.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hi.cord.first.price.entity.Price;
import com.hi.cord.first.price.repository.PriceRepository;

@Service("priceService")
@Transactional
public class PriceServiceImpl implements PriceService {

	@Autowired
	private PriceRepository priceRepository;

	@Override
	public Price findById(Long id) {
		Price price = priceRepository.findById(id);
		return price;
	}

	@Override
	public void savePrice(Price price) {
		priceRepository.insert(price);
	}

	@Override
	public void updatePrice(Price price) {
		Price dbPrice = priceRepository.findById(price.getPriceId());
		if (price.getPriceDelCheck().equals("0")) {
			dbPrice.setPriceDelCheck(price.getPriceDelCheck());
			dbPrice.setPriceId(dbPrice.getPriceId());
		} else {
			dbPrice = price;
			dbPrice.setPriceId(dbPrice.getPriceId());
		}
	}

	@Override
	public void deletePriceById(Long id) {
		priceRepository.deleteById(id);
	}

	@Override
	public List<Price> findAllPrices(Price price) {
		List<Price> prices = priceRepository.findAllPrices(price);
		return prices;
	}
}