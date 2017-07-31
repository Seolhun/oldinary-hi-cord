package com.hi.cord.first.comment.repository;

import java.util.List;

import com.hi.cord.common.model.Paging;
import com.hi.cord.first.comment.entity.Comment;

public interface CommentDAO {
	
	Comment findById(Long id);
	
//	User findByEmail(String email);
	
	void save(Comment board);

	void delete(Long id);
	
	int getCount(Paging paging);
	
	List<Comment> findAll(Paging paging);

}
