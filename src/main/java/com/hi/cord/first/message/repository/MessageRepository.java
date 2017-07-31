package com.hi.cord.first.message.repository;

import java.util.List;

import com.hi.cord.first.message.entity.HistoryMessage;


public interface MessageRepository {
	void insert(HistoryMessage historyMessage);
	
	HistoryMessage selectById(Long id);
	
	List<HistoryMessage> selectList(HistoryMessage historyMessage);
	
	boolean delete(Long id);
}
