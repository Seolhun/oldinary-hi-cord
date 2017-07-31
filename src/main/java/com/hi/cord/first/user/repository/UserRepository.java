package com.hi.cord.first.user.repository;

import java.util.List;

import com.hi.cord.first.user.entity.User;

public interface UserRepository {

	User selectById(Long id);
	
	User selectByEmail(String email);
	
	User selectByPhone(String phone);
	
	void save(User user);
	
	void deleteByEmail(String email);
	
	List<User> findAllUsers(User user);
	
}

