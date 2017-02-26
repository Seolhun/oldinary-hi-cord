package com.hi.cord.first.stadium.repository;

import java.util.List;

import com.hi.cord.common.model.Paging;
import com.hi.cord.first.stadium.model.Stadium;

public interface StadiumDAO {
	
	List<Stadium> findAll(Stadium stadium);
	
	Stadium findById(Long id);
	
	void save(Stadium stadium);

	void delete(Long id);
	
	int getCount(Paging paging);
}
