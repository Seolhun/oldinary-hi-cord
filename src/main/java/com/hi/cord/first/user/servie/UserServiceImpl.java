package com.hi.cord.first.user.servie;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hi.cord.common.model.CommonState;
import com.hi.cord.first.user.entity.User;
import com.hi.cord.first.user.repository.UserRepository;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository dao;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public User selectById(Long usreId) {
		log.info("param : selectById : {} ", usreId.toString());
		return dao.selectById(usreId);
	}
	
	@Override
	public User selectByEmail(String userEmail) {
		log.info("param : selectByEmail : {} ", userEmail.toString());
		User user = dao.selectByEmail(userEmail);
		return user;
	}
	
	@Override
	public User selectByPhone(String userPhone) {
		log.info("param : selectByPhone : {} ", userPhone.toString());
		User user = dao.selectByPhone(userPhone);
		return user;
	}
	
	@Override
	public void insert(User user) {
		log.info("param : save : {} ", user.toString());
		user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
		user.setUserState(CommonState.ACTIVE.getState());
		dao.save(user);
	}
	
	@Override
	public void update(User user) {
		log.info("param : update : {} ", user.toString());
		User entity = dao.selectByEmail(user.getUserEmail());
		if (entity != null) {
			if(user.getUserPassword()!=null && user.getType()==1) {
				entity.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
			}
			if(user.getUserState()!=null) {
				entity.setUserState(user.getUserState());
			}
			if(user.getUserProfiles()!=null) {
				entity.setUserProfiles(user.getUserProfiles());
			}
			if(user.getUserLockedAuth()!=null) {
				entity.setUserLockedAuth(user.getUserLockedAuth());
			}
		} 
	}
	
	@Override
	public void deleteByEmail(String userEmail) {
		log.info("param : deleteUserByEmail : {} ", userEmail.toString());
		dao.deleteByEmail( userEmail);
	}
	
	@Override
	//=> Or 일때 하나만이라도 true이면 true이다., And 일때 하나만이라도 false이면 false이다.
	public boolean isUserEmailUnique(User user) {
		log.info("param : isUserEmailUnique : {} ", user.toString());
		User dbUser = selectByEmail(user.getUserEmail());
		return (dbUser == null || ((dbUser.getUserEmail() == null && dbUser.getUserEmail() != user.getUserEmail())));
	}
	
	@Override
	//=> Or 일때 하나만이라도 true이면 true이다., And 일때 하나만이라도 false이면 false이다.
	public boolean isUserPhoneUnique(User user) {
		log.info("param : isUserPhoneUnique : {} ", user.toString());
		User dbUser = selectByPhone(user.getUserPhone());
		return (dbUser == null || ((dbUser.getUserPhone() == null && dbUser.getUserPhone() != user.getUserPhone())));
	}
	
	@Override
	public List<User> selectList(User user) {
		log.info("param : findAllUsers : {} ", user.toString());
		return dao.findAllUsers(user);
	}
}