package com.brain.home.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brain.home.entity.user.UserProfile;
import com.brain.home.repository.user.UserProfileDao;

@Service("userProfileService")
public class UserProfileServiceImpl implements UserProfileService {

	@Autowired
	UserProfileDao dao;

	@Override
	public UserProfile findById(int id) {
		return dao.findById(id);
	}

	@Override
	public UserProfile findByType(String type) {
		return dao.findByType(type);
	}

	@Override
	public List<UserProfile> findAll() {
		return dao.findAll();
	}
}
