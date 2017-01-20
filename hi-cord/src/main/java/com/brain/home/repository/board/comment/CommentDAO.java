package com.brain.home.repository.board.comment;

import java.util.List;

import com.brain.home.entity.board.Comment;
import com.brain.home.entity.common.Paging;

public interface CommentDAO {
	
	Comment findById(Long id);
	
//	User findByEmail(String email);
	
	void save(Comment board);

	void delete(Long id);
	
	int getCount(Paging paging);
	
	List<Comment> findAll(Paging paging);

}
