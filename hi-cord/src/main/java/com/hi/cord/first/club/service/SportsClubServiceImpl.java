package com.hi.cord.first.club.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hi.cord.first.club.entity.SportsClub;
import com.hi.cord.first.club.repository.SportsClubRepository;


@Transactional
@Service("sportsClubService")
public class SportsClubServiceImpl implements SportsClubService {
	static final Logger log = LoggerFactory.getLogger(SportsClubServiceImpl.class);
	
	@Autowired
	protected SportsClubRepository sportsClubRepository;
	
	@Override
	public void insert(SportsClub sportsClub) {
		log.info("param : "+sportsClub.toString());
		sportsClubRepository.insert(sportsClub);
	}

	@Override
	public List<SportsClub> findAll(SportsClub sportsClub) {
		log.info("param : "+sportsClub.toString());
		
		List<SportsClub> sportsClubList = sportsClubRepository.findAll(sportsClub);
		log.info("return : "+sportsClubList.toString());
		return sportsClubList;
	}
	
	@Override
	public SportsClub findById(Long id) {
		log.info("param : "+id.toString());
		
		SportsClub sportsClub=sportsClubRepository.findById(id);
		log.info("return : "+sportsClub.toString());
		return sportsClub;
	}

	@Override
	public Boolean delete(Long id) {
		log.info("param : "+id.toString());
		
		SportsClub dbSportsClub = sportsClubRepository.findById(id);
		log.info("return : "+dbSportsClub);
		if (dbSportsClub != null) {
			sportsClubRepository.delete(dbSportsClub.getSportsClubId());
			return true;
		}
		return false;
	}

	@Override
	public SportsClub update(SportsClub sportsClub) {
		log.info("param : "+sportsClub.toString());
		SportsClub dbSportsClub = sportsClubRepository.findById(sportsClub.getSportsClubId());
		log.info("return : "+dbSportsClub);
		if (dbSportsClub != null) {
			dbSportsClub.setSportsClubName(sportsClub.getSportsClubName());
		}
		return dbSportsClub;
	}
}
