package com.brain.home.repository.user;

import java.util.List;

import com.brain.home.entity.common.Paging;
import com.brain.home.entity.user.User;

public interface UserDao {

	User findById(Long id);
	
	User findByEmail(String email);
	
	User findByPhone(String phone);
	
	void save(User user);
	
	void deleteByEmail(String email);
	
	int getCount(Paging paging);
	
	List<User> findAllUsers(Paging paging);
	
}

