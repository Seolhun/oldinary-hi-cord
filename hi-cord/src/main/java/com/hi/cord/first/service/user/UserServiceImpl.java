package com.hi.cord.first.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hi.cord.common.model.Paging;
import com.hi.cord.first.entity.user.State;
import com.hi.cord.first.entity.user.User;
import com.hi.cord.first.repository.user.UserDao;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao dao;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public User findById(Long id) {
		return dao.findById(id);
	}
	
	@Override
	public User findByEmail(String email) {
		User user = dao.findByEmail(email);
		return user;
	}
	
	@Override
	public User findByPhone(String phone) {
		User user = dao.findByPhone(phone);
		return user;
	}
	
	@Override
	public void saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setState(State.ACTIVE.getState());
		dao.save(user);
	}
	
	@Override
	public void updateUser(User user) {
		User entity = dao.findByEmail(user.getEmail());
		if (entity != null) {
			if(user.getPassword()!=null) {
				entity.setPassword(passwordEncoder.encode(user.getPassword()));
			}
		} 
		entity.setState(user.getState());
		entity.setUserProfiles(user.getUserProfiles());
	}
	
	@Override
	public void deleteUserByEmail(String email) {
		dao.deleteByEmail(email);
	}
	
	@Override
	public int getCount(Paging paging) {
		return dao.getCount(paging);
	}
	
	@Override
	public List<User> findAllUsers(Paging paging) {
		return dao.findAllUsers(paging);
	}

	@Override
	//=> Or 일때 하나만이라도 true이면 true이다., And 일때 하나만이라도 false이면 false이다.
	public boolean isUserEmailUnique(String email) {
		User user = findByEmail(email);
		return (user == null || ((email == null && user.getEmail() != email)));
	}
}