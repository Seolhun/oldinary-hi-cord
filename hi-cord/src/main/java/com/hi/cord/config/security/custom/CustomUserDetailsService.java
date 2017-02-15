package com.hi.cord.config.security.custom;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hi.cord.first.entity.user.State;
import com.hi.cord.first.entity.user.User;
import com.hi.cord.first.entity.user.UserProfile;
import com.hi.cord.first.service.user.UserService;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

	@Autowired
	UserService userService;
	
	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		try {
			User user = userService.findByEmail(email);
			logger.info("User : {}", user);
			if (user == null) {
				logger.info("User not found");
				throw new UsernameNotFoundException("User not found");
			}
			return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getState().equals(State.ACTIVE.getState()), true, true, true, getGrantedAuthorities(user));
		} catch (Exception e){
			throw new UsernameNotFoundException("Not user");
		}
	}

	private List<GrantedAuthority> getGrantedAuthorities(User user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for (UserProfile userProfile : user.getUserProfiles()) {
			logger.info("UserProfile : {}", userProfile);
			authorities.add(new SimpleGrantedAuthority("ROLE_" + userProfile.getType()));
		}
		
		logger.info("authorities : {}", authorities);
		return authorities;
	}

}
