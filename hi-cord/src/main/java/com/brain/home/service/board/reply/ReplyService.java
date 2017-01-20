package com.brain.home.service.board.reply;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.brain.home.entity.board.Reply;
import com.brain.home.entity.common.Paging;

@Transactional(propagation=Propagation.REQUIRED, transactionManager="txManager", noRollbackFor={NullPointerException.class})
public interface ReplyService {

	public Reply findById(Long id);

	public void save(Reply reply);

	public Reply update(Reply reply);

	public Boolean delete(Long id);

	int getCount(Paging paging);

	public List<Reply> findAll(Paging paging);

}
