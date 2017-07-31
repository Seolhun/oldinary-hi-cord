package com.hi.cord.first.message.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hi.cord.first.message.entity.HistoryMessage;


@Transactional(propagation=Propagation.REQUIRED, transactionManager="txManager", noRollbackFor={NullPointerException.class})
public interface MessageService {
	public void insert(HistoryMessage historyMessage);

	public HistoryMessage selectById(Long id);
	
	public List<HistoryMessage> selectList(HistoryMessage historyMessage);
	
	public HistoryMessage update(HistoryMessage historyMessage);

	public Boolean delete(Long id);
}
