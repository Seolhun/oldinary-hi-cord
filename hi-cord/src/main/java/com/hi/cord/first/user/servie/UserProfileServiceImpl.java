package com.hi.cord.first.user.servie;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hi.cord.first.user.entity.UserProfile;
import com.hi.cord.first.user.repository.UserProfileDao;

@Service("userProfileService")
public class UserProfileServiceImpl implements UserProfileService {
	static final Logger log = LoggerFactory.getLogger(UserProfileServiceImpl.class);
	
	@Autowired
	UserProfileDao dao;

	@Override
	public UserProfile findById(int id) {
		log.info("Parameter : "+id);
		return dao.findById(id);
	}

	@Override
	public UserProfile findByType(String type) {
		log.info("Parameter : "+type);
		return dao.findByType(type);
	}

	@Override
	public List<UserProfile> findAll() {
		return dao.findAll();
	}
}
