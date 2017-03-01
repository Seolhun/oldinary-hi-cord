package com.hi.cord.first.club.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hi.cord.first.club.entity.SportsClub;

@Transactional(propagation=Propagation.REQUIRED, transactionManager="txManager", noRollbackFor={NullPointerException.class})
public interface SportsClubService {
	public void insert(SportsClub sportsClub);

	public SportsClub findById(Long id);
	
	public List<SportsClub> findAll(SportsClub sportsClub);
	
	public SportsClub update(SportsClub sportsClub);

	public Boolean delete(Long id);
}
