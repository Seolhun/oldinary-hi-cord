package com.hi.cord.first.service.board.comment;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hi.cord.first.entity.board.Comment;
import com.hi.cord.common.model.Paging;

@Transactional(propagation=Propagation.REQUIRED, transactionManager="txManager", noRollbackFor={NullPointerException.class})
public interface CommentService {

	public Comment findById(Long id);

	public void save(Comment reply);

	public Comment update(Comment reply);

	public Boolean delete(Long id);

	int getCount(Paging paging);

	public List<Comment> findAll(Paging paging);

}
