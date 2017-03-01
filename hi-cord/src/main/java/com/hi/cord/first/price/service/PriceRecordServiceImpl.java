package com.hi.cord.first.price.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hi.cord.first.price.entity.PriceRecord;
import com.hi.cord.first.price.repository.PriceRecordRepository;

@Service("priceRecordService")
@Transactional
public class PriceRecordServiceImpl implements PriceRecordService {
	
	@Autowired
	private PriceRecordRepository priceRecordRepository;

	@Override
	public PriceRecord findById(Long id) {
		return priceRecordRepository.findById(id);
	}

	@Override
	public void savePriceRecord(PriceRecord priceRecord) {
		priceRecordRepository.insert(priceRecord);
	}

	@Override
	public void updatePriceRecord(PriceRecord priceRecord) {
		PriceRecord dbPriceRecord=priceRecordRepository.findById(priceRecord.getPriceRecordId());
		dbPriceRecord=priceRecord;
		dbPriceRecord.setPriceRecordId(priceRecord.getPriceRecordId());
	}

	@Override
	public void deletePriceRecordById(Long id) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<PriceRecord> findAllPriceRecords(PriceRecord priceRecord) {
		return priceRecordRepository.findAllPriceRecords(priceRecord);
	}
}