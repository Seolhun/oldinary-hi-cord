package com.brain.home.config.security.custom;
//package com.shun.blog.security;
//
//import java.io.IOException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.DisabledException;
//import org.springframework.security.authentication.LockedException;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//
//import com.shun.blog.controller.common.CommonFn;
//import com.shun.blog.dao.user.UserDao;
//import com.shun.blog.model.user.User;
//import com.shun.blog.model.user.UserAttempts;
//
//public class LimitingDaoAuthenticationProvider extends DaoAuthenticationProvider {
//
//	@Autowired
//	UserDao dao;
//
//	@Autowired
//	CommonFn commonFn;
//
//	// 로그인 이후 성공 시 이전 시도 횟수를 초기화, 실패 시 catch 예외 처리
//	@Override
//	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//		try {
//			// 스프링 시큐리티 안의 form-login 정보를 가져와서 인증 정보와 비교
//			Authentication auth = super.authenticate(authentication);
//			// 로그인 성공 플래그 넣기
//			dao.updateUserState(-1, authentication.getName(), State.ACTIVE.getState());
//			User user = dao.getUserbyEmail(authentication.getName());
//			String ip = cFn.getUserIP();
//			UserAttempts useraattempts = new UserAttempts(user.getId(), 0, ip);
//			dao.insertFailAttempts(useraattempts);
//			return auth;
//		}
//		// 유효하지 않은 패스워드 입력 시 해당 회원의 시도 횟수 증가
//		catch (BadCredentialsException e) {
//			insertFailAttempts(authentication.getName());
//			throw e;
//		}
//		// 잠긴 회원은 로그인 페이지에 경고 메세지 노출
//		catch (LockedException e) {
//			throw e;
//		} catch (DisabledException e) {
//			throw e;
//		}
//	}
//
//	// attemp 테이블에 몇번 시도했는지를 넣고, 5회 이상이 되면 state를 locked으로 변경하면 된다.
//	private void insertFailAttempts(String username) {
//		// 로그인하는 아이디 값으로 유저정보를 담아온다.
//		User dbUser = dao.getUserbyEmail(username);
//		// 유저가 로그인 시도를 실패한 정보가 있는지를 조회한다.
//		UserAttempts userDBAttempts = dao.getUserAttemptsInfo(dbUser.getId());
//		String ip = cFn.getUserIP();
//
//		// 유저 로그인 시도를 실패한 정보가 없다면, 로그인 정보를 인서트한다.
//		if (userDBAttempts != null) {
//			int maxUserAttempts = userDBAttempts.getAttempts();
//			// 5 이하일 경우
//			if (maxUserAttempts < 5) {
//				UserAttempts UserAttempts = new UserAttempts(dbUser.getId(), maxUserAttempts + 1, ip);
//				dao.insertFailAttempts(UserAttempts);
//			} else {
//				// 5 이상일 경우 락 걸고 메일 보내기
//				dao.updateUserState(-1, username, State.LOCKED.getState());
//
//				String sendToEmail = dbUser.getEmail();
//				String sha = commonFn.buildRandomSHA256(username);
//				System.out.println("MyLog:" + username);
//				System.out.println("MyLog:" + sha);
//				dao.updateUserAuth(userDBAttempts.getKey(), sha);
//
//				// 메일을 보낸다.
//				try {
//					userCFn.lockedMailSend(sendToEmail, sha);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//				throw new LockedException("5회 이상 비밀번호를 틀려 회원 계정이 잠겼습니다!! 이메일로 확인하세요.");
//
//			}
//		} else {
//			UserAttempts UserAttempts = new UserAttempts(dbUser.getId(), 1, ip);
//			dao.insertFailAttempts(UserAttempts);
//		}
//
//	}
//}