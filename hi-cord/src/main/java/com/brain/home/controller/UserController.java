package com.brain.home.controller;

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

import com.brain.home.controller.common.CommonFn;
import com.brain.home.entity.common.Paging;
import com.brain.home.entity.user.User;
import com.brain.home.entity.user.UserProfile;
import com.brain.home.entity.user.UserProfileType;
import com.brain.home.service.price.PriceRecordService;
import com.brain.home.service.user.UserProfileService;
import com.brain.home.service.user.UserService;

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

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(ModelMap model) throws Exception {
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
	
	@RequestMapping(value = "/myinfo", method = RequestMethod.GET)
	public String myInfo(ModelMap model, HttpServletResponse response, HttpServletRequest request) {
		String email="";
		try {
			email=cFn.getPrincipal();
		} catch (NullPointerException e) {
			logger.info("My Information Not Login User Error : Nullpoint");
			return "redirect:/";
		}
		
		//Login User 정보
		User user = new User();
		user=userService.findByEmail(email);
		
		//Paging
		int currentPage = cFn.checkVDInt(request.getParameter("cPage"), 1);
		int sType = cFn.checkVDInt(request.getParameter("sType"), -1);
		String rawQuestion = request.getParameter("sText");
		int sDate = cFn.checkVDInt(request.getParameter("sDate"), 0);
		int limit = cFn.checkVDInt(request.getParameter("limit"), 20);
		Paging paging = new Paging(currentPage, sType, rawQuestion, sDate, limit);
		
		//Paing을 위한 총 갯수		
		int totalCount = priceRecordService.getCount(paging);
		paging.setTotalPage(totalCount);
		setPaging(paging);

		model.addAttribute("user", user);
		model.addAttribute("edit", false);
		return "views/user/myinfo";
	}

	@RequestMapping(value = { "/signup" }, method = RequestMethod.POST)
	public String signupDo(@Valid User user, BindingResult result, ModelMap model, HttpServletRequest request) throws Exception {
		String email=user.getEmail();
		String phone=user.getPhone();
		String name=user.getName();
		String mapping = "views/user/signup";
		if (result.hasErrors()) {
			// 개인 별로 에러메세지 띄우기 구현 예정(개인별로 해도 메세지가 2개뜨는 문제 발생)
			return mapping;
		}
		
		if (name==null || name.length()==0) {
			return mapping;
		}
		
		// 유저 권한 넣기(프론트에서 값을 받지 않기때문에 백엔드에서 넣어준다.)
		if (!userService.isUserEmailUnique(email)) {
			validCheckAndSendError(messageSource, result, request, email, "user", "email", "non.unique.email");
			return mapping;
		} else if (!userService.isUserPhoneUnique(phone)) {
			validCheckAndSendError(messageSource, result, request, phone, "user", "phone", "non.unique.phone");
			return mapping;
		}
		
		Set<UserProfile> upSet = new HashSet<>();
		UserProfile up = new UserProfile();
		up.setId(UserProfileType.GUEST.ordinal() + 1);
		up.setType(UserProfileType.GUEST.getType());
		upSet.add(up);
		user.setUserProfiles(upSet);
		userService.saveUser(user);
		return "redirect:/";
	}

	@ResponseBody
	@RequestMapping(value = { "/signup/duplicate/email" }, method = RequestMethod.POST)
	public String emailduDupl(@RequestBody User user, HttpServletRequest request, BindingResult result) {
		String email=user.getEmail();
		if (!userService.isUserEmailUnique(email)) {
			return "false";
		} 
		return "true";
	}
	
	@ResponseBody
	@RequestMapping(value = { "/signup/duplicate/phone" }, method = RequestMethod.POST)
	public String phoneDupl(@RequestBody User user, HttpServletRequest request, BindingResult result) {
		String phone=user.getPhone();
		if (!userService.isUserPhoneUnique(phone)) {
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

	void validCheckAndSendError(MessageSource messageSource, BindingResult result, HttpServletRequest request,
			String getValue, String objectName, String fieldName, String messagePropertyName) {
		FieldError error = new FieldError(objectName, fieldName,
				messageSource.getMessage(messagePropertyName, new String[] { getValue }, request.getLocale()));
		result.addError(error);
	}
	
	/*----------- 페이징 메소드 Start ------------------------------------------------------------*/
	private Paging setPaging(Paging paging) {
		int blockLimit = 10; // 페이지 당 보여줄 블록 번호 limit
								// [1],[2],[3],[4],[5],[6],[7],[8],[9],[10]
		int totalPage = paging.getTotalPage();
		int limit = paging.getLimit();
		int cPage = paging.getCPage();

		int totalBlock = totalPage / limit + (totalPage % limit > 0 ? 1 : 0); // 전체
		int currentBlock = cPage / blockLimit + (cPage % blockLimit > 0 ? 1 : 0);// 현재
		int blockEndNo = currentBlock * blockLimit;
		int blockStartNo = blockEndNo - (blockLimit - 1);

		if (blockEndNo > totalBlock) {
			blockEndNo = totalBlock;
		}

		int prev_cPage = blockStartNo - blockLimit; // << *[이전]*
		int next_cPage = blockStartNo + blockLimit; // *[다음]* >>

		if (prev_cPage < 1) {
			prev_cPage = 1;
		}

		if (next_cPage > totalBlock) {
			next_cPage = totalBlock / blockLimit * blockLimit + 1;
		}
		paging.setBlockLimit(blockLimit);
		paging.setCurrentBlock(currentBlock);
		paging.setTotalBlock(totalBlock);
		paging.setBlockEndNo(blockEndNo);
		paging.setBlockStartNo(blockStartNo);
		paging.setNext_cPage(next_cPage);
		paging.setPrev_cPage(prev_cPage);
		return paging;
	}
}