package com.hi.cord.first.comment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hi.cord.first.comment.entity.Comment;
import com.hi.cord.first.comment.repository.CommentDAO;
import com.hi.cord.common.model.Paging;

@Service("commentService")
@Transactional
public class CommentServiceImpl implements CommentService {

	@Autowired
	protected CommentDAO dao;

	@Override
	public void save(Comment comment) {
		dao.save(comment);
	}

	@Override
	public Boolean delete(Long id) {
		Comment temp = dao.findById(id);
		
		if (temp != null) {
			dao.delete(temp.getCommentId());
			return true;
		}
		return false;
	}

	@Override
	public Comment update(Comment comment) {
		Comment entity = dao.findById(comment.getCommentId());
		if (entity != null) {
			entity.setCommentContent(comment.getCommentContent());
		}
		return entity;
	}

	@Override
	public Comment findById(Long id) {
		return dao.findById(id);
	}

	@Override
	public List<Comment> findAll(Paging paging) {
		return dao.findAll(paging);
	}

	@Override
	public int getCount(Paging paging) {
		return dao.getCount(paging);
	}

}
