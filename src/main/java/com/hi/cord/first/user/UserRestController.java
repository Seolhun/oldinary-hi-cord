package com.hi.cord.first.user;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hi.cord.common.model.AjaxResult;
import com.hi.cord.common.service.CommonService;
import com.hi.cord.first.club.entity.SportsClub;
import com.hi.cord.first.price.service.PriceRecordService;
import com.hi.cord.first.stadium.model.Stadium;
import com.hi.cord.first.user.entity.User;
import com.hi.cord.first.user.servie.UserProfileService;
import com.hi.cord.first.user.servie.UserService;

@RestController
@RequestMapping("/user")
public class UserRestController {
	static final Logger log = LoggerFactory.getLogger(UserRestController.class);

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
	CommonService cFn;

	
	/**
	 * User insert
	 * 
	 * @param Stadium
	 * @return AjaxResult
	 * @throws Exception
	 */
	@RequestMapping(value = { "/insert" }, method = RequestMethod.POST)
	public AjaxResult insertStadium(@RequestBody @Valid SportsClub sportsClub, ModelMap model, BindingResult result, Principal principal, AjaxResult ajaxResult) throws Exception{
		
		return ajaxResult;
	}

	/**
	 * 유저 Select
	 * 
	 * @param Stadium
	 * @return AjaxResult
	 * @throws Exception
	 */
	@RequestMapping(value = { "/one" }, method = RequestMethod.GET)
	public Stadium selectOne(@Valid Stadium stadium, ModelMap model) throws Exception{
		
		
		return stadium;
	}
	
	/**
	 * User SelectList
	 * 
	 * @param Stadium
	 * @return AjaxResult
	 * @throws Exception
	 */
	@RequestMapping(value = { "/list" }, method = RequestMethod.GET)
	public List<Stadium> selectList(@Valid Stadium stadium, ModelMap model) throws Exception{
		
		List<Stadium> stadiumList=new ArrayList<>();
		return stadiumList;
	}
	
	/**
	 * 유저 Update
	 * 
	 * @param Stadium
	 * @return AjaxResult
	 * @throws Exception
	 */
	@RequestMapping(value = { "/update/{path}" }, method = RequestMethod.PUT)
	public AjaxResult updateStadium(@Valid Stadium stadium, @PathVariable Long path, ModelMap model, AjaxResult ajaxResult) throws Exception{
		ajaxResult.setResult("fail");
		
		
		return ajaxResult;
	}
	
	
	/**
	 * User delete
	 * 
	 * @param Stadium
	 * @return AjaxResult
	 * @throws Exception
	 */
	@RequestMapping(value = { "/delete/{path}" }, method = RequestMethod.DELETE)
	public AjaxResult deleteStadium(@Valid Stadium stadium,  @PathVariable Long path, ModelMap model, AjaxResult ajaxResult) throws Exception{
		ajaxResult.setResult("fail");
		
		
		return ajaxResult;
	}
	
	/**
	 * User email duplication Check
	 * 
	 * @param SportClub
	 * @return AjaxResult
	 * @throws Exception
	 */
	@RequestMapping(value = { "/signup/duplicate/email-json" }, method = RequestMethod.POST)
	public AjaxResult emailduDupl(@RequestBody User user, HttpServletRequest request, AjaxResult ajaxResult) {
		if (!userService.isUserEmailUnique(user)) {
			ajaxResult.setResult("fail");
			return ajaxResult;
		}
		ajaxResult.setResult("success");
		return ajaxResult;
	}
	
	/**
	 * User phone duplication Check
	 * 
	 * @param SportClub
	 * @return AjaxResult
	 * @throws Exception
	 */
	@RequestMapping(value = { "/signup/duplicate/phone-json" }, method = RequestMethod.POST)
	public AjaxResult phoneduDupl(@RequestBody User user, HttpServletRequest request, AjaxResult ajaxResult) {
		if (!userService.isUserPhoneUnique(user)) {
			ajaxResult.setResult("fail");
			return ajaxResult;
		}
		ajaxResult.setResult("success");
		return ajaxResult;
	}
}