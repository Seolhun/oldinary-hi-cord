package com.hi.cord.first.user.servie;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hi.cord.first.user.entity.UserAttempts;
@Transactional(propagation=Propagation.REQUIRED, transactionManager="txManager", noRollbackFor={NullPointerException.class})
public interface UserAttemptsService {
	
	void insert(UserAttempts userAttempts);

	UserAttempts findById(Long id);
	
	UserAttempts findByEmail(String email);
	
	List<UserAttempts> findAll(UserAttempts userAttempts);
	
	void update(UserAttempts userAttempts);

	Boolean delete(Long id);
}
