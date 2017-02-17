package com.hi.cord.first.repository.board.reply;

import java.util.List;

import com.hi.cord.first.entity.board.Reply;
import com.hi.cord.common.model.Paging;

public interface ReplyDAO {
	
	Reply findById(Long id);
	
//	User findByEmail(String email);
	
	void save(Reply board);

	void delete(Long id);
	
	int getCount(Paging paging);
	
	List<Reply> findAll(Paging paging);

}
