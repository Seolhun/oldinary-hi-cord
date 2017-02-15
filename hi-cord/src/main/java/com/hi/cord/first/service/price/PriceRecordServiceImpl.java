package com.hi.cord.first.service.price;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hi.cord.common.model.Paging;
import com.hi.cord.first.entity.price.PriceRecord;
import com.hi.cord.first.repository.price.PriceRecordDao;

@Service("priceRecordService")
@Transactional
public class PriceRecordServiceImpl implements PriceRecordService {
	
	@Autowired
	private PriceRecordDao priceRecordDao;

	@Override
	public PriceRecord findById(Long id) {
		return priceRecordDao.findById(id);
	}

	@Override
	public void savePriceRecord(PriceRecord priceRecord) {
		priceRecordDao.save(priceRecord);
	}

	@Override
	public void updatePriceRecord(PriceRecord priceRecord) {
		PriceRecord dbPriceRecord=priceRecordDao.findById(priceRecord.getId());
		dbPriceRecord=priceRecord;
		dbPriceRecord.setId(priceRecord.getId());
	}

	@Override
	public void deletePriceRecordById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<PriceRecord> findAllPriceRecords(Paging paging) {
		return priceRecordDao.findAllPriceRecords(paging);
	}

	@Override
	public int getCount(Paging paging) {
		return priceRecordDao.getCount(paging);
	}
}