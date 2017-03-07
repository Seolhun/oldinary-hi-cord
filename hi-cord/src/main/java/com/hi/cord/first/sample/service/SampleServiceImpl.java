package com.hi.cord.first.sample.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hi.cord.first.sample.repository.SampleRepository;
import com.hi.cord.first.stadium.model.Stadium;


@Transactional
@Service("sampleService")
public class SampleServiceImpl implements SampleService {
	static final Logger log = LoggerFactory.getLogger(SampleServiceImpl.class);
	
	@Autowired
	protected SampleRepository sampleRepository;
	
	@Override
	public void insert(Stadium stadium) {
		log.info("param : "+stadium.toString());
		sampleRepository.insert(stadium);
	}
	
	@Override
	public Stadium selectById(Long id) {
		log.info("param : "+id.toString());
		
		Stadium stadium=sampleRepository.selectById(id);
		log.info("return : "+stadium.toString());
		return stadium;
	}

	@Override
	public List<Stadium> selectList(Stadium stadium) {
		log.info("param : "+stadium.toString());
		
		List<Stadium> stadiumList = sampleRepository.selectList(stadium);
		log.info("return : "+stadiumList.toString());
		return stadiumList;
	}

	@Override
	public Boolean delete(Long id) {
		log.info("param : "+id.toString());
		try {
			sampleRepository.delete(id);	
		} catch (NullPointerException e) {
			return false;
		}
		return true;
	}

	@Override
	public Stadium update(Stadium stadium) {
		log.info("param : "+stadium.toString());
		Stadium dbStadium = sampleRepository.selectById(stadium.getStadiumId());
		log.info("return : "+dbStadium);
		if (dbStadium != null) {
			dbStadium.setStadiumName(stadium.getStadiumName());
		}
		return dbStadium;
	}
}
