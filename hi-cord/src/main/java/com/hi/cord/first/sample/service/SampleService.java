package com.hi.cord.first.sample.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hi.cord.first.stadium.model.Stadium;

@Transactional(propagation=Propagation.REQUIRED, transactionManager="txManager", noRollbackFor={NullPointerException.class})
public interface SampleService {
	public void insert(Stadium stadium);

	public Stadium findById(Long id);
	
	public List<Stadium> findAll(Stadium stadium);
	
	public Stadium update(Stadium stadium);

	public Boolean delete(Long id);
}
