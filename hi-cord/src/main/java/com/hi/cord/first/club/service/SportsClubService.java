package com.hi.cord.first.club.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hi.cord.first.club.entity.SportsClub;

@Transactional(propagation=Propagation.REQUIRED, transactionManager="txManager", noRollbackFor={NullPointerException.class})
public interface SportsClubService {
	void insert(SportsClub sportsClub);

	SportsClub selectById(Long id);
	
	SportsClub selectByName(String name);
		
	SportsClub selectByTel(String tel);
	
	List<SportsClub> selectList(SportsClub sportsClub);
	
	SportsClub update(SportsClub sportsClub);

	Boolean delete(Long id);
	
	boolean isSportsClubNameUnique(SportsClub sportsClub);
	
	boolean isSportsClubTelUnique(SportsClub sportsClub);
}
