package com.hi.cord.first.file.repository;

import java.util.List;

import com.hi.cord.common.model.Paging;
import com.hi.cord.first.file.entity.FileData;

public interface FileDataDAO {
	
	List<FileData> findAll(FileData fileData);
	
	FileData findById(Long id);
	
	void save(FileData fileData);

	void delete(Long id);
	
	int getCount(Paging paging);
}
