package com.hi.cord.first.user.repository;

import java.util.List;

import com.hi.cord.first.user.entity.UserAttempts;

public interface UserAttemptsRepository {
	
	void insert(UserAttempts userAttempts);
	
	UserAttempts selectById(Long id);
	
	UserAttempts selectByEmail(String email);
	
	List<UserAttempts> selectList(UserAttempts userAttempts);
	
	boolean delete(Long id);
}
