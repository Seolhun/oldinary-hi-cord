package com.hi.cord.first.repository.file;

import java.util.List;

import com.hi.cord.common.model.Paging;
import com.hi.cord.first.entity.file.FileData;

public interface FileDataDAO {
	
	List<FileData> findAll(FileData fileData);
	
	FileData findById(Long id);
	
	void save(FileData fileData);

	void delete(Long id);
	
	int getCount(Paging paging);
}
