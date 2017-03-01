package com.hi.cord.first.user.servie;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hi.cord.first.user.entity.UserAttempts;
import com.hi.cord.first.user.repository.UserAttemptsRepository;

@Service("userAttemptsService")
public class UserAttemptsServiceImpl implements UserAttemptsService {
	static final Logger log = LoggerFactory.getLogger(UserAttemptsServiceImpl.class);

	@Autowired
	UserAttemptsRepository userAttemptsRepository;
	
	@Override
	public void insert(UserAttempts userAttempts) {
		userAttemptsRepository.insert(userAttempts);
	}

	@Override
	public UserAttempts findById(Long id) {
		return userAttemptsRepository.findById(id);
	}
	
	@Override
	public UserAttempts findByEmail(String email) {
		return userAttemptsRepository.findByEmail(email);
	}

	@Override
	public List<UserAttempts> findAll(UserAttempts userAttempts) {
		return userAttemptsRepository.findAll(userAttempts);
	}

	@Override
	public void update(UserAttempts userAttempts) {
		log.info("Parameter : "+userAttempts.toString());
		UserAttempts entity = userAttemptsRepository.findByEmail(userAttempts.getUserAttemptsEmail());
		if (entity != null) {
			if(userAttempts.getUserAttemptsSuccessFlag()!=null) {
				entity.setUserAttemptsSuccessFlag(userAttempts.getUserAttemptsSuccessFlag());
			}
		} 
	}

	@Override
	public Boolean delete(Long id) {
		return userAttemptsRepository.delete(id);
	}
	
	
}
