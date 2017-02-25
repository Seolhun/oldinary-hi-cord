package com.hi.cord.first.file.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hi.cord.common.model.Paging;
import com.hi.cord.first.file.entity.FileData;
import com.hi.cord.first.file.repository.FileDataDAO;


@Transactional
@Service("fileDataService")
public class FileDataServiceImpl implements FileDataService {
	static final Logger log = LoggerFactory.getLogger(FileDataServiceImpl.class);
	
	@Autowired
	protected FileDataDAO fileDataDao;

	@Override
	public List<FileData> findAll(FileData fileData) {
		log.info("param : "+fileData.toString());
		
		List<FileData> boardList = fileDataDao.findAll(fileData);
		log.info("return : "+fileData.toString());
		return boardList;
	}
	
	@Override
	public FileData findById(Long id) {
		log.info("param : "+id.toString());
		
		FileData fileData=fileDataDao.findById(id);
		log.info("return : "+fileData.toString());
		return fileData;
	}
	
	@Override
	public void save(FileData fileData) {
		log.info("param : "+fileData.toString());
		fileDataDao.save(fileData);
	}

	@Override
	public Boolean delete(Long id) {
		log.info("param : "+id.toString());
		
		FileData dbFileData= fileDataDao.findById(id);
		log.info("return : "+dbFileData);
		if (dbFileData != null) {
			fileDataDao.delete(dbFileData.getFileDataId());
			return true;
		}
		return false;
	}

	@Override
	public FileData update(FileData fileData) {
		log.info("param : "+fileData.toString());
		FileData dbFileData = fileDataDao.findById(fileData.getFileDataId());
		log.info("return : "+dbFileData);
		if (dbFileData != null) {
			dbFileData.setFileDataSavedPath(fileData.getFileDataSavedPath());
			dbFileData.setFileDataOriginName(fileData.getFileDataOriginName());
			dbFileData.setFileDataSavedName(fileData.getFileDataSavedName());
		}
		return dbFileData;
	}

	@Override
	public int getCount(Paging paging) {
		log.info("param : "+paging.toString());
		
		int count=fileDataDao.getCount(paging);
		log.info("return : "+count);
		return count;
	}

}
