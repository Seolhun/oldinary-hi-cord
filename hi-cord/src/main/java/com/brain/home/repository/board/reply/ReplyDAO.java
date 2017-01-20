package com.brain.home.repository.board.reply;

import java.util.List;

import com.brain.home.entity.board.Reply;
import com.brain.home.entity.common.Paging;

public interface ReplyDAO {
	
	Reply findById(Long id);
	
//	User findByEmail(String email);
	
	void save(Reply board);

	void delete(Long id);
	
	int getCount(Paging paging);
	
	List<Reply> findAll(Paging paging);

}
