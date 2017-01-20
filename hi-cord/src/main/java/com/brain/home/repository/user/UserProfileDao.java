package com.brain.home.repository.user;

import java.util.List;

import com.brain.home.entity.user.UserProfile;

public interface UserProfileDao {

	List<UserProfile> findAll();

	UserProfile findByType(String type);

	UserProfile findById(int id);
}
