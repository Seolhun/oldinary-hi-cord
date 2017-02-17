package com.hi.cord.first.service.user;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao dao;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public User findById(Long id) {
		log.info("Parameter : "+id);
		return dao.findById(id);
	}
	
	@Override
	public User findByEmail(String email) {
		log.info("Parameter : "+email);
		User user = dao.findByEmail(email);
		return user;
	}
	
	@Override
	public User findByPhone(String phone) {
		log.info("Parameter : "+phone);
		User user = dao.findByPhone(phone);
		return user;
	}
	
	@Override
	public void saveUser(User user) {
		log.info("Parameter : "+user.toString());
		user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
		user.setUserState(State.ACTIVE.getState());
		dao.save(user);
	}
	
	@Override
	public void updateUser(User user) {
		log.info("Parameter : "+user.toString());
		User entity = dao.findByEmail(user.getUserEmail());
		if (entity != null) {
			if(user.getUserPassword()!=null) {
				entity.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
			}
		} 
		entity.setUserState(user.getUserState());
		entity.setUserProfiles(user.getUserProfiles());
	}
	
	@Override
	public void deleteUserByEmail(String email) {
		log.info("Parameter : "+email);
		dao.deleteByEmail(email);
	}
	
	@Override
	public int getCount(Paging paging) {
		log.info("Parameter : "+paging);
		return dao.getCount(paging);
	}
	
	@Override
	public List<User> findAllUsers(Paging paging) {
		log.info("Parameter : "+paging);
		return dao.findAllUsers(paging);
	}

	@Override
	//=> Or 일때 하나만이라도 true이면 true이다., And 일때 하나만이라도 false이면 false이다.
	public boolean isUserEmailUnique(String email) {
		log.info("Parameter : "+email);
		User user = findByEmail(email);
		return (user == null || ((email == null && user.getUserEmail() != email)));
	}
}