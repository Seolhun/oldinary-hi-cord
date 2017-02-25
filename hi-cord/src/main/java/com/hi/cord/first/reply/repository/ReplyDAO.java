package com.hi.cord.first.reply.repository;

import java.util.List;

import com.hi.cord.common.model.Paging;
import com.hi.cord.first.reply.entity.Reply;

public interface ReplyDAO {
	
	Reply findById(Long id);
	
//	User findByEmail(String email);
	
	void save(Reply board);

	void delete(Long id);
	
	int getCount(Paging paging);
	
	List<Reply> findAll(Paging paging);

}
