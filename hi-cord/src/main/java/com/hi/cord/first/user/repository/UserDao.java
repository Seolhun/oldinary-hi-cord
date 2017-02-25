package com.hi.cord.first.user.repository;

import java.util.List;

import com.hi.cord.common.model.Paging;
import com.hi.cord.first.user.entity.User;

public interface UserDao {

	User findById(Long id);
	
	User findByEmail(String email);
	
	User findByPhone(String phone);
	
	void save(User user);
	
	void deleteByEmail(String email);
	
	int getCount(Paging paging);
	
	List<User> findAllUsers(Paging paging);
	
}

