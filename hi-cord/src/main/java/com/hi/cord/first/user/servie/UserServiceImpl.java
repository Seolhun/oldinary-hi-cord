package com.hi.cord.first.user.servie;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hi.cord.common.model.Paging;
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
	public User findById(Long usreId) {
		log.info("Parameter : "+usreId);
		return dao.findById(usreId);
	}
	
	@Override
	public User findByEmail(String userEmail) {
		log.info("Parameter : "+userEmail);
		User user = dao.findByEmail(userEmail);
		return user;
	}
	
	@Override
	public User findByPhone(String userPhone) {
		log.info("Parameter : "+userPhone);
		User user = dao.findByPhone(userPhone);
		return user;
	}
	
	@Override
	public void saveUser(User user) {
		log.info("Parameter : "+user.toString());
		user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
		user.setUserState(CommonState.ACTIVE.getState());
		dao.save(user);
	}
	
	@Override
	public void updateUser(User user) {
		log.info("Parameter : "+user.toString());
		User entity = dao.findByEmail(user.getUserEmail());
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
	public void deleteUserByEmail(String userEmail) {
		log.info("Parameter : "+ userEmail);
		dao.deleteByEmail( userEmail);
	}
	
	@Override
	//=> Or 일때 하나만이라도 true이면 true이다., And 일때 하나만이라도 false이면 false이다.
	public boolean isUserEmailUnique(User user) {
		log.info("Parameter : "+user.getUserEmail());
		User dbUser = findByEmail(user.getUserEmail());
		return (dbUser == null || ((dbUser.getUserEmail() == null && dbUser.getUserEmail() != user.getUserEmail())));
	}
	
	@Override
	//=> Or 일때 하나만이라도 true이면 true이다., And 일때 하나만이라도 false이면 false이다.
	public boolean isUserPhoneUnique(User user) {
		log.info("Parameter : "+user.getUserPhone());
		User dbUser = findByPhone(user.getUserPhone());
		return (dbUser == null || ((dbUser.getUserPhone() == null && dbUser.getUserPhone() != user.getUserPhone())));
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
}