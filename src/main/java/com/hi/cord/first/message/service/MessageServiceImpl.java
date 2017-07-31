package com.hi.cord.first.message.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hi.cord.first.message.entity.HistoryMessage;
import com.hi.cord.first.message.repository.MessageRepository;


@Transactional
@Service("messageService")
public class MessageServiceImpl implements MessageService {
	static final Logger log = LoggerFactory.getLogger(MessageServiceImpl.class);
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Override
	public void insert(HistoryMessage historyMessage) {
		log.info("param : "+historyMessage.toString());
		messageRepository.insert(historyMessage);
	}
	
	@Override
	public HistoryMessage selectById(Long id) {
		log.info("param : "+id.toString());
		
		HistoryMessage historyMessage=messageRepository.selectById(id);
		log.info("return : "+historyMessage.toString());
		return historyMessage;
	}

	@Override
	public List<HistoryMessage> selectList(HistoryMessage historyMessage) {
		log.info("param : "+historyMessage.toString());
		
		List<HistoryMessage> historyMessageList = messageRepository.selectList(historyMessage);
		log.info("return : "+historyMessageList.toString());
		return historyMessageList;
	}

	@Override
	public Boolean delete(Long id) {
		log.info("param : "+id.toString());
		try {
			messageRepository.delete(id);	
		} catch (NullPointerException e) {
			return false;
		}
		return true;
	}

	@Override
	public HistoryMessage update(HistoryMessage historyMessage) {
		log.info("param : "+historyMessage.toString());
		HistoryMessage dbHistoryMessage = messageRepository.selectById(historyMessage.getHistoryMessageId());
		log.info("return : "+dbHistoryMessage);
		if (dbHistoryMessage != null) {
			dbHistoryMessage.setHistoryMessageContent(historyMessage.getHistoryMessageContent());
		}
		return dbHistoryMessage;
	}
}
