package com.hi.cord.first.service.board.reply;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hi.cord.first.entity.board.Reply;
import com.hi.cord.common.model.Paging;

@Transactional(propagation=Propagation.REQUIRED, transactionManager="txManager", noRollbackFor={NullPointerException.class})
public interface ReplyService {

	public Reply findById(Long id);

	public void save(Reply reply);

	public Reply update(Reply reply);

	public Boolean delete(Long id);

	int getCount(Paging paging);

	public List<Reply> findAll(Paging paging);

}
