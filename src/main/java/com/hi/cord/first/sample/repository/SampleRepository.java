package com.hi.cord.first.sample.repository;

import java.util.List;

import com.hi.cord.first.stadium.model.Stadium;

public interface SampleRepository {
	void insert(Stadium stadium);
	
	Stadium selectById(Long id);
	
	List<Stadium> selectList(Stadium stadium);
	
	boolean delete(Long id);
}
