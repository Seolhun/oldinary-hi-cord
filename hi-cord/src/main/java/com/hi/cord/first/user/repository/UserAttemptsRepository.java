package com.hi.cord.first.user.repository;

import java.util.List;

import com.hi.cord.first.user.entity.UserAttempts;

public interface UserAttemptsRepository {
	
	void insert(UserAttempts userAttempts);
	
	UserAttempts findById(Long id);
	
	UserAttempts findByEmail(String email);
	
	List<UserAttempts> findAll(UserAttempts userAttempts);
	
	boolean delete(Long id);
}
