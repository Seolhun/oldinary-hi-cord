package com.hi.cord.first.file.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hi.cord.common.model.Paging;
import com.hi.cord.first.file.entity.FileData;

@Transactional(propagation=Propagation.REQUIRED, transactionManager="txManager", noRollbackFor={NullPointerException.class})
public interface FileDataService {

	public List<FileData> findAll(FileData FileData);
	
	public FileData findById(Long id);

	public void save(FileData FileData);

	public FileData update(FileData FileData);

	public Boolean delete(Long id);

	int getCount(Paging paging);
}
