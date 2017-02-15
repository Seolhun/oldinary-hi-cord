package com.hi.cord.first.service.user;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hi.cord.common.model.Paging;
import com.hi.cord.first.entity.user.User;

@Transactional(propagation=Propagation.REQUIRED, transactionManager="txManager", noRollbackFor={NullPointerException.class})
public interface UserService {

	User findById(Long id);

	User findByEmail(String email);
	
	User findByPhone(String nickName);

	void saveUser(User user);

	void updateUser(User user);
	
	void deleteUserByEmail(String email);

	List<User> findAllUsers(Paging paging);
	
	boolean isUserEmailUnique(String email);

	int getCount(Paging paging);
}