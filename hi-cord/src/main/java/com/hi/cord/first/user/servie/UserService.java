package com.hi.cord.first.user.servie;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hi.cord.first.user.entity.User;

@Transactional(propagation=Propagation.REQUIRED, transactionManager="txManager", noRollbackFor={NullPointerException.class})
public interface UserService {

	User selectById(Long usreId);

	User selectByEmail(String userEmail);
	
	User selectByPhone(String userPhone);

	void insert(User user);

	void update(User user);
	
	boolean isUserEmailUnique(User user);
	
	boolean isUserPhoneUnique(User user);
	
	void deleteByEmail(String userEmail);

	List<User> selectList(User uesr);
}