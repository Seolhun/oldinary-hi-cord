package com.hi.cord.first.sample.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hi.cord.first.stadium.model.Stadium;
import com.hi.cord.first.stadium.repository.StadiumDAO;


@Transactional
@Service("stadiumService")
public class SampleServiceImpl implements SampleService {
	static final Logger log = LoggerFactory.getLogger(SampleServiceImpl.class);
	
	@Autowired
	protected StadiumDAO stadiumDao;
	
	@Override
	public void insert(Stadium stadium) {
		log.info("param : "+stadium.toString());
		stadiumDao.save(stadium);
	}

	@Override
	public List<Stadium> findAll(Stadium stadium) {
		log.info("param : "+stadium.toString());
		
		List<Stadium> stadiumList = stadiumDao.findAll(stadium);
		log.info("return : "+stadiumList.toString());
		return stadiumList;
	}
	
	@Override
	public Stadium findById(Long id) {
		log.info("param : "+id.toString());
		
		Stadium stadium=stadiumDao.findById(id);
		log.info("return : "+stadium.toString());
		return stadium;
	}

	@Override
	public Boolean delete(Long id) {
		log.info("param : "+id.toString());
		
		Stadium dbStadium = stadiumDao.findById(id);
		log.info("return : "+dbStadium);
		if (dbStadium != null) {
			stadiumDao.delete(dbStadium.getStadiumId());
			return true;
		}
		return false;
	}

	@Override
	public Stadium update(Stadium stadium) {
		log.info("param : "+stadium.toString());
		Stadium dbStadium = stadiumDao.findById(stadium.getStadiumId());
		log.info("return : "+dbStadium);
		if (dbStadium != null) {
			dbStadium.setStadiumName(stadium.getStadiumName());
		}
		return dbStadium;
	}
}
