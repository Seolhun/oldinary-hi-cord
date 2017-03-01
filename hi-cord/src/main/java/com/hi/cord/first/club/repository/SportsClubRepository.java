package com.hi.cord.first.club.repository;

import java.util.List;

import com.hi.cord.first.club.entity.SportsClub;

public interface SportsClubRepository {
	void insert(SportsClub sportsClub);
	
	SportsClub findById(Long id);
	
	List<SportsClub> findAll(SportsClub sportsClub);
	
	boolean delete(Long id);
}
