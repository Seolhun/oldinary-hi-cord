package com.hi.cord.first.message.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hi.cord.common.repository.AbstractRepository;
import com.hi.cord.first.message.entity.HistoryMessage;

@Repository("messageRepository")
public class MessageRepositoryImpl extends AbstractRepository<Long, HistoryMessage> implements MessageRepository {
	static final Logger log = LoggerFactory.getLogger(MessageRepositoryImpl.class);

	@Override
	public void insert(HistoryMessage historyMessage) {
		persist(historyMessage);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<HistoryMessage> selectList(HistoryMessage historyMessage) {
		log.info("TEST : findAll"+historyMessage.toString());
		Criteria criteria = createEntityCriteria().addOrder(Order.desc("historyMessageId")).add(Restrictions.eq("historyMessageDelCheck", 0))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<HistoryMessage> historyMessages =criteria.list();
		log.info("Parameter : "+historyMessages.toString());
		return historyMessages;
	}
	
	@Override
	public HistoryMessage selectById(Long id) {
		HistoryMessage historyMessage = getByKey(id);
		return historyMessage;
	}

	@Override
	public boolean delete(Long id) {
		return delete(id);
	}
}
