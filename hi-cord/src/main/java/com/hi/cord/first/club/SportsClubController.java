package com.hi.cord.first.club;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hi.cord.common.service.CommonService;
import com.hi.cord.first.club.entity.SportsClub;
import com.hi.cord.first.club.service.SportsClubService;

@Controller
@RequestMapping("/club")
public class SportsClubController {
	static final Logger log = LoggerFactory.getLogger(SportsClubController.class);

	@Autowired
	private SportsClubService sportsClubService;
	@Autowired
	private CommonService cFn;

	/**
	 * 클럽 등록 페이지 이동.
	 * 
	 * @param String
	 * @return String - view
	 * @throws Exception
	 */
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String moveClubRegisterPage(SportsClub sportsClub ,ModelMap model) throws Exception{
		model.addAttribute("sportsClub", sportsClub);
		return "views/club/club-insert";
	}
	
	/**
	 * 클럽 등록.
	 * 
	 * @param String
	 * @return String - view
	 * @throws Exception
	 */
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String inertClub(@Valid SportsClub sportsClub, ModelMap model, BindingResult result, HttpServletRequest request, MessageSource messageSource) throws Exception{
		String mapping = "views/user/user-signup";
		String sportsClubName=sportsClub.getSportsClubName();
		String sportsClubPassowrd=sportsClub.getSportsClubPassword();
		String sportsClubTel=sportsClub.getSportsClubTel();
		String sportsClubAddress=sportsClub.getSportsClubAddress();
		String sportsClubAddres2s=sportsClub.getSportsClubAddress2();
		
		// 개인 별로 에러메세지 띄우기 구현 예정(개인별로 해도 메세지가 2개뜨는 문제 발생)
		model.addAttribute("sportsClub", sportsClub);
		if (sportsClubName==null || sportsClubName.length()==0) {
			cFn.validCheckAndSendError(messageSource, result, request, sportsClubName, "sportsClub", "sportsClubName", "INVALID-NAME");
			return mapping;
		} else if(sportsClubPassowrd==null || sportsClubPassowrd.length()==0){
			cFn.validCheckAndSendError(messageSource, result, request, sportsClubPassowrd, "sportsClub", "sportsClubPassowrd", "INVALID-PASSWORD");
			return mapping;
		} else if(sportsClubTel==null || sportsClubTel.length()==0){
			cFn.validCheckAndSendError(messageSource, result, request, sportsClubName, "sportsClubTel", "sportsClubTel", "INVALID-TEL");
			return mapping;
		} else if((sportsClubAddress==null || sportsClubAddress.length()==0) || (sportsClubAddres2s==null || sportsClubAddres2s.length()==0)){
			cFn.validCheckAndSendError(messageSource, result, request, sportsClubName, "sportsClub", "sportsClubAddress", "INVALID-ADDRESS");
			return mapping;
		} else if (result.hasErrors()) {
			return mapping;
		}
		
		if (!sportsClubService.isSportsClubNameUnique(sportsClub)) {
			cFn.validCheckAndSendError(messageSource, result, request, sportsClubName, "sportsClub", "sportsClubName", "NON-UNIQUE-SC-NAME");
			return mapping;
		} else if (!sportsClubService.isSportsClubTelUnique(sportsClub)) {
			cFn.validCheckAndSendError(messageSource, result, request, sportsClubTel, "sportsClub", "sportsClubTel", "NON-UNIQUE-SC-TEL");
			return mapping;
		}
		return "views/club/club-insert";
	}
	
	/**
	 * 클럽 수정 페이지 이동.
	 * 
	 * @param String
	 * @return String - view
	 * @throws Exception
	 */
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public String moveClubModify() throws Exception{
		return "views/club/club-update";
	}
//	
//	/**
//	 * 클럽 페이지 이동.
//	 * 
//	 * @param String
//	 * @return String - view
//	 * @throws Exception
//	 */
//	public String movePage() throws Exception{
//		return "page/stadium";
//	}
}