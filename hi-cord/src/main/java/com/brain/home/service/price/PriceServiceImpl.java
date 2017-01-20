package com.brain.home.service.price;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brain.home.entity.common.Paging;
import com.brain.home.entity.price.Price;
import com.brain.home.repository.price.PriceDao;

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
		Price dbPrice = pDao.findById(price.getId());
		if (price.getDelCheck() == 1) {
			dbPrice.setDelCheck(price.getDelCheck());
			dbPrice.setId(dbPrice.getId());
		} else {
			dbPrice = price;
			dbPrice.setId(dbPrice.getId());
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