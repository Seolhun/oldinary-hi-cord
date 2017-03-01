package com.hi.cord.first.user.repository;

import java.util.List;

import com.hi.cord.first.user.entity.UserProfile;

public interface UserProfileRepository {

	List<UserProfile> findAll();

	UserProfile findByType(String type);

	UserProfile findById(int id);
}
