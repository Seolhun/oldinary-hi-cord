package com.hi.cord.first.club.repository;

import java.util.List;

import com.hi.cord.first.club.entity.SportsClub;

public interface SportsClubRepository {
	void insert(SportsClub sportsClub);
	
	SportsClub selectById(Long id);
	
	SportsClub selectByName(String name);
	
	SportsClub selectByTel(String tel);
	
	List<SportsClub> selectList(SportsClub sportsClub);
	
	boolean delete(Long id);
}
