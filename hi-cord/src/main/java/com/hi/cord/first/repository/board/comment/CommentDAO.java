package com.hi.cord.first.repository.board.comment;

import java.util.List;

import com.hi.cord.first.entity.board.Comment;
import com.hi.cord.common.model.Paging;

public interface CommentDAO {
	
	Comment findById(Long id);
	
//	User findByEmail(String email);
	
	void save(Comment board);

	void delete(Long id);
	
	int getCount(Paging paging);
	
	List<Comment> findAll(Paging paging);

}
