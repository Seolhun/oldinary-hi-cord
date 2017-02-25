package com.hi.cord.first.price.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hi.cord.common.model.Paging;
import com.hi.cord.first.price.entity.Price;
import com.hi.cord.first.price.repository.PriceDao;

@Service("priceService")
@Transactional
public class PriceServiceImpl implements PriceService {

	@Autowired
	private PriceDao pDao;

	@Override
	public Price findById(Long id) {
		Price price = pDao.findById(id);
		return price;
	}

	@Override
	public void savePrice(Price price) {
		pDao.save(price);
	}

	@Override
	public void updatePrice(Price price) {
		Price dbPrice = pDao.findById(price.getPriceId());
		if (price.getPriceDelCheck().equals("N")) {
			dbPrice.setPriceDelCheck(price.getPriceDelCheck());
			dbPrice.setPriceId(dbPrice.getPriceId());
		} else {
			dbPrice = price;
			dbPrice.setPriceId(dbPrice.getPriceId());
		}
	}

	@Override
	public void deletePriceById(Long id) {
		pDao.deleteById(id);
	}

	@Override
	public List<Price> findAllPrices(Paging paging) {
		List<Price> prices = pDao.findAllPrices(paging);
		return prices;
	}

	@Override
	public int getCount(Paging paging) {
		int count = pDao.getCount(paging);
		return count;
	}

}