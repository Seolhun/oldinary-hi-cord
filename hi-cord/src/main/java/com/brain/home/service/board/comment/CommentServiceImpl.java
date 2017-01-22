package com.brain.home.service.board.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brain.home.entity.board.Comment;
import com.brain.home.entity.common.Paging;
import com.brain.home.repository.board.comment.CommentDAO;

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
			dao.delete(temp.getId());
			return true;
		}
		return false;
	}

	@Override
	public Comment update(Comment comment) {
		Comment entity = dao.findById(comment.getId());
		if (entity != null) {
			entity.setContent(comment.getContent());
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