package com.hi.cord.first.comment.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hi.cord.common.model.Paging;
import com.hi.cord.first.comment.entity.Comment;

@Transactional(propagation=Propagation.REQUIRED, transactionManager="txManager", noRollbackFor={NullPointerException.class})
public interface CommentService {

	public Comment findById(Long id);

	public void save(Comment reply);

	public Comment update(Comment reply);

	public Boolean delete(Long id);

	int getCount(Paging paging);

	public List<Comment> findAll(Paging paging);

}
