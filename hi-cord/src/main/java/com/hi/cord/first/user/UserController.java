package com.hi.cord.first.user;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hi.cord.common.service.CommonService;
import com.hi.cord.first.price.service.PriceRecordService;
import com.hi.cord.first.user.entity.User;
import com.hi.cord.first.user.entity.UserProfile;
import com.hi.cord.first.user.entity.UserProfileType;
import com.hi.cord.first.user.servie.UserService;

@Controller
@RequestMapping("/user")
@Transactional(propagation = Propagation.REQUIRED, transactionManager = "txManager", noRollbackFor = {NullPointerException.class })
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	PriceRecordService priceRecordService;

	@Autowired
	MessageSource messageSource;	

	@Autowired
	PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

	@Autowired
	AuthenticationTrustResolver authenticationTrustResolver;

	@Autowired
	CommonService cFn;

	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(ModelMap model, @RequestParam(value = "error", required = false) String error, HttpServletRequest request) throws Exception {
		log.info("loginPage");
		if (isCurrentAuthenticationAnonymous()) {
			if (error != null) {
				Exception exception = (Exception)request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
				model.addAttribute("errorMsg", getErrorMessage(exception));
			}
			return "views/user/user-login";
		} else {
			return "redirect:/";
		}
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(ModelMap model, HttpServletResponse res) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("edit", false);
		return "views/user/user-signup";
	}
	
	@RequestMapping(value = { "/signup" }, method = RequestMethod.POST)
	public String signupDo(@Valid User user, BindingResult result, ModelMap model, HttpServletRequest request) throws Exception {
		String mapping = "views/user/user-signup";
		String email=user.getUserEmail();
		String phone=user.getUserPhone();
		String name=user.getUserName();
		
		// 개인 별로 에러메세지 띄우기 구현 예정(개인별로 해도 메세지가 2개뜨는 문제 발생)
		model.addAttribute("user", user);
		if (email==null || email.length()==0) {
			return mapping;
		} else if(phone==null || phone.length()==0){
			return mapping;
		} else if(name==null || name.length()==0){
			return mapping;
		} else if (result.hasErrors()) {
			return mapping;
		}
		
		if (!userService.isUserEmailUnique(user)) {
			cFn.validCheckAndSendError(messageSource, result, request, email, "user", "userEmail", "NON-UNIQUE-USER-EMAIL");
			return mapping;
		} else if (!userService.isUserPhoneUnique(user)) {
			cFn.validCheckAndSendError(messageSource, result, request, phone, "user", "userPhone", "NON-UNIQUE-USER-PHONE");
			return mapping;
		}
		
		// 유저 권한 넣기(프론트에서 값을 받지 않기때문에 백엔드에서 넣어준다.)
		Set<UserProfile> upSet = new HashSet<>();
		UserProfile up = new UserProfile();
		up.setUserProfileId(UserProfileType.PLAYER.ordinal() + 1);
		up.setUserProfileType(UserProfileType.PLAYER.getType());
		upSet.add(up);
		user.setUserProfiles(upSet);
		
		userService.insert(user);
		
		return "redirect:/";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			persistentTokenBasedRememberMeServices.logout(request, response, auth);
			SecurityContextHolder.getContext().setAuthentication(null);
		}
		return "redirect:/user/login?logout";
	}
	

	/**
	 * 스타디움 페이지 이동.
	 * 
	 * @param -
	 * @return String - view
	 * @throws Exception
	 */
	@RequestMapping(value = "/myinfo", method = RequestMethod.GET)
	public String myInfoPage(ModelMap model, Authentication auth) throws Exception {
		log.info("MyInfo");
		User user=userService.selectByEmail(auth.getName());
		
		model.addAttribute("user", user);
		return "/views/user/user-myinfo";
	}

	private boolean isCurrentAuthenticationAnonymous() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authenticationTrustResolver.isAnonymous(authentication);
	}

	// 커스텀 된 로그인 에러 메세지
	private String getErrorMessage(Exception exception) {
		String error = "";
		if (exception instanceof LockedException) {
			error = "현재 계정이 잠겼습니다.";
		} else if (exception instanceof DisabledException) {
			error = "현재 계정이 이용 불가능합니다.";
//		} else if (exception instanceof RecapException) {
//			error = "현재 계정이 이용 불가능합니다.";
		} else {
			error = "계정과 비밀번호를 올바르게 입력해주세요,.";
		}
		return error;
	}
}