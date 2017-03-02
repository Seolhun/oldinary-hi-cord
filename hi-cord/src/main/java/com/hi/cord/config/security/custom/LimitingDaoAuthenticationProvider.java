package com.hi.cord.config.security.custom;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.hi.cord.common.model.CommonState;
import com.hi.cord.common.service.CommonService;
import com.hi.cord.first.user.entity.User;
import com.hi.cord.first.user.entity.UserAttempts;
import com.hi.cord.first.user.servie.UserAttemptsService;
import com.hi.cord.first.user.servie.UserService;

public class LimitingDaoAuthenticationProvider extends DaoAuthenticationProvider {
	static final Logger log = LoggerFactory.getLogger(LimitingDaoAuthenticationProvider.class);
	@Autowired
	UserService userService;
	
	@Autowired
	UserAttemptsService userAttemptsService;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder; 

	@Autowired
	CommonService commonService;

	// 로그인 이후 성공 시 이전 시도 횟수를 초기화, 실패 시 catch 예외 처리
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		try {
			// 스프링 시큐리티 안의 form-login 정보를 가져와서 인증 정보와 비교
			Authentication auth = super.authenticate(authentication);
			
			//로그인 시도한 유저정보를 찾기.
			User user = userService.findByEmail(authentication.getName());
			String ip = commonService.getUserIP();
			log.info("param : "+user.toString());
			
			// 로그인 성공 플래그 넣기
			UserAttempts userAttempts = new UserAttempts(user.getUserEmail(), 0 , ip, 0);
			userAttemptsService.insert(userAttempts);
			return auth;
		}
		// 유효하지 않은 패스워드 입력 시 해당 회원의 시도 횟수 증가
		catch (BadCredentialsException e) {
			insertFailAttempts(authentication.getName());
			throw e;
		}
		// 잠긴 회원은 로그인 페이지에 경고 메세지 노출
		catch (LockedException e) {
			throw e;
		} 
		// 사용 불가능한 로그인시 경고 메세지 노출.
		catch (DisabledException e) {
			throw e;
		}
		catch (NullPointerException e) {
			throw e;
		}
	}

	// attemp 테이블에 몇번 시도했는지를 넣고, 5회 이상이 되면 state를 locked으로 변경하면 된다.
	private void insertFailAttempts(String username) throws BadCredentialsException{
		log.info("param - username : {} "+username.toString());
		
		// 로그인하는 아이디 값으로 유저정보를 담아온다.
		User dbUser = userService.findByEmail(username);
		
		// 유저가 로그인 시도를 실패한 정보가 있는지를 조회한다.
		UserAttempts userDBAttempts=new UserAttempts();
		String loginIp="";
		try {
			userDBAttempts = userAttemptsService.findByEmail(dbUser.getUserEmail());
			loginIp = commonService.getUserIP();
		} catch (NullPointerException e) {
			throw new BadCredentialsException("error");
		}
		
		//유저 로그인 시도를 실패한 정보가 있다면, 최고값을 찾아서 +1를 인서트해준다.
		if (userDBAttempts != null) {
			Integer maxUserAttempts = userDBAttempts.getUserAttemptsCounts();
			// 5 이하일 경우
			if (maxUserAttempts < 5) {
				UserAttempts userAttempts = new UserAttempts(dbUser.getUserEmail(), maxUserAttempts + 1, loginIp, 1);
				userAttemptsService.insert(userAttempts);
			} else {
				// 5 이상일 경우 계정을 Locked으로 바꾸기.
				String auth=bCryptPasswordEncoder.encode(dbUser.getUserEmail());
				dbUser.setType(1);
				dbUser.setUserState(CommonState.LOCKED.getState());
				dbUser.setUserLockedAuth(auth);
				userService.update(dbUser);

				// 메일을 보낸다.
				try {
					commonService.sendEmailLockingUser(dbUser.getUserEmail(), dbUser.getUserName(), auth, "http://localhost:9000/user/unlock", auth.substring(0,10));
				} catch (IOException e) {
					log.info("Sending Email is Error");
				}
				throw new LockedException("5회 이상 비밀번호를 틀려 회원 계정이 잠겼습니다!! 이메일로 확인하세요.");
			}
		} 
		//유저 로그인 시도를 실패한 정보가 없다면, 로그인 정보를 인서트한다.
		else {
			UserAttempts UserAttempts = new UserAttempts(dbUser.getUserEmail(), 1, loginIp, 1);
			userAttemptsService.insert(UserAttempts);
		}
	}
}