package com.hi.cord.first.stadium.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hi.cord.common.model.Paging;
import com.hi.cord.first.stadium.model.Stadium;

@Transactional(propagation=Propagation.REQUIRED, transactionManager="txManager", noRollbackFor={NullPointerException.class})
public interface StadiumService {
	public void save(Stadium stadium);

	public List<Stadium> findAll(Stadium stadium);
	
	public Stadium findById(Long id);

	public Stadium update(Stadium stadium);

	public Boolean delete(Long id);

	int getCount(Paging paging);
}
