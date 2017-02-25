package com.hi.cord.first.user.servie;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hi.cord.common.model.Paging;
import com.hi.cord.first.user.entity.User;

@Transactional(propagation=Propagation.REQUIRED, transactionManager="txManager", noRollbackFor={NullPointerException.class})
public interface UserService {

	User findById(Long usreId);

	User findByEmail(String userEmail);
	
	User findByPhone(String userPhone);

	void saveUser(User user);

	void updateUser(User user);
	
	boolean isUserEmailUnique(User user);
	
	boolean isUserPhoneUnique(User user);
	
	void deleteUserByEmail(String userEmail);

	List<User> findAllUsers(Paging paging);

	int getCount(Paging paging);
}