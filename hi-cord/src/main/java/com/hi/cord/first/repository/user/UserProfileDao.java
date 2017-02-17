package com.hi.cord.first.repository.user;

import java.util.List;

import com.hi.cord.first.entity.user.UserProfile;

public interface UserProfileDao {

	List<UserProfile> findAll();

	UserProfile findByType(String type);

	UserProfile findById(int id);
}
