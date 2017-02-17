package com.hi.cord.first.controller.user;

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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hi.cord.common.CommonFn;
import com.hi.cord.first.entity.user.User;
import com.hi.cord.first.entity.user.UserProfile;
import com.hi.cord.first.entity.user.UserProfileType;
import com.hi.cord.first.service.price.PriceRecordService;
import com.hi.cord.first.service.user.UserProfileService;
import com.hi.cord.first.service.user.UserService;

@Controller
@Transactional(propagation = Propagation.REQUIRED, transactionManager = "txManager", noRollbackFor = {NullPointerException.class })
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	PriceRecordService priceRecordService;

	@Autowired
	MessageSource messageSource;

	@Autowired
	UserProfileService userProfileService;

	@Autowired
	PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

	@Autowired
	AuthenticationTrustResolver authenticationTrustResolver;

	@Autowired
	CommonFn cFn;

	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(ModelMap model) throws Exception {
		log.info("loginPage");
		if (isCurrentAuthenticationAnonymous()) {
			return "views/user/login";
		} else {
			return "redirect:/";
		}
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(ModelMap model, HttpServletResponse res) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("edit", false);
		return "views/user/signup";
	}
	
	@RequestMapping(value = { "/signup" }, method = RequestMethod.POST)
	public String signupDo(@Valid User user, BindingResult result, ModelMap model, HttpServletRequest request) throws Exception {
		String email=user.getUserEmail();
		String name=user.getUserName();
		String mapping = "views/user/signup";
		// 개인 별로 에러메세지 띄우기 구현 예정(개인별로 해도 메세지가 2개뜨는 문제 발생)
		if (result.hasErrors()) {
			return mapping;
		}
		
		if (name==null || name.length()==0) {
			return mapping;
		}
		
		// 유저 권한 넣기(프론트에서 값을 받지 않기때문에 백엔드에서 넣어준다.)
		if (!userService.isUserEmailUnique(email)) {
			validCheckAndSendError(messageSource, result, request, email, "user", "userEmail", "non.unique.email");
			return mapping;
		}
		
		Set<UserProfile> upSet = new HashSet<>();
		UserProfile up = new UserProfile();
		up.setUserProfileId(UserProfileType.GUEST.ordinal() + 1);
		up.setUserProfileType(UserProfileType.GUEST.getType());
		upSet.add(up);
		user.setUserProfiles(upSet);
		userService.saveUser(user);
		return "redirect:/";
	}

	@ResponseBody
	@RequestMapping(value = { "/signup/duplicate/email" }, method = RequestMethod.POST)
	public String emailduDupl(@RequestBody User user, HttpServletRequest request, BindingResult result) {
		String email=user.getUserEmail();
		if (!userService.isUserEmailUnique(email)) {
			return "false";
		} 
		return "true";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			persistentTokenBasedRememberMeServices.logout(request, response, auth);
			SecurityContextHolder.getContext().setAuthentication(null);
		}
		return "redirect:/login?logout";
	}

	private boolean isCurrentAuthenticationAnonymous() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authenticationTrustResolver.isAnonymous(authentication);
	}

	void validCheckAndSendError(MessageSource messageSource, BindingResult result, HttpServletRequest request, String getValue, String objectName, String fieldName, String messagePropertyName) {
		FieldError error = new FieldError(objectName, fieldName,
				messageSource.getMessage(messagePropertyName, new String[] { getValue }, request.getLocale()));
		result.addError(error);
	}
}