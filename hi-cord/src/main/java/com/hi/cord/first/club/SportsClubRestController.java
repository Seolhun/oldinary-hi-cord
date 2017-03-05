package com.hi.cord.first.club;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.hi.cord.first.club.service.SportsClubService;

@RestController
@RequestMapping("/club")
public class SportsClubRestController {
	static final Logger log = LoggerFactory.getLogger(SportsClubRestController.class);

	@Autowired
	private SportsClubService sportsClubService;
	@Autowired
	private CommonService cFn;

	/**
	 * 클럽 인서트
	 * 
	 * @param SportsClub
	 * @return AjaxResult
	 * @throws Exception
	 */
	@RequestMapping(value = { "/insert-json" }, method = RequestMethod.POST)
	public AjaxResult insertSportsClub(@RequestBody @Valid SportsClub sportsClub, ModelMap model, BindingResult result, Principal principal, AjaxResult ajaxResult) throws Exception{
		
		return ajaxResult;
	}
	
	/**
	 * 클럽 Select
	 * 
	 * @param SportsClub
	 * @return AjaxResult
	 * @throws Exception
	 */
	@RequestMapping(value = { "/one-json" }, method = RequestMethod.GET)
	public SportsClub selectOne(@Valid SportsClub sportsClub, ModelMap model) throws Exception{
		
		
		return sportsClub;
	}
	
	/**
	 * 클럽 SelectList
	 * 
	 * @param SportsClub
	 * @return AjaxResult
	 * @throws Exception
	 */
	@RequestMapping(value = { "/list-json" }, method = RequestMethod.GET)
	public List<SportsClub> selectList(@Valid SportsClub sportsClub, ModelMap model) throws Exception{
		
		List<SportsClub> sportsClubList=new ArrayList<>();
		return sportsClubList;
	}
	
	/**
	 * 클럽 Update
	 * 
	 * @param SportsClub
	 * @return AjaxResult
	 * @throws Exception
	 */
	@RequestMapping(value = { "/update/{path}" }, method = RequestMethod.PUT)
	public AjaxResult updateSportsClub(@Valid SportsClub sportsClub, @PathVariable Long path, ModelMap model, AjaxResult ajaxResult) throws Exception{
		ajaxResult.setResult("fail");
		
		
		return ajaxResult;
	}
	
	/**
	 * 클럽 delete
	 * 
	 * @param SportsClub
	 * @return AjaxResult
	 * @throws Exception
	 */
	@RequestMapping(value = { "/delete/{path}" }, method = RequestMethod.DELETE)
	public AjaxResult deleteSportsClub(@Valid SportsClub sportsClub,  @PathVariable Long path, ModelMap model, AjaxResult ajaxResult) throws Exception{
		ajaxResult.setResult("fail");
		
		
		return ajaxResult;
	}
	
	@RequestMapping(value = { "/insert/duplicate/name-json" }, method = RequestMethod.POST)
	public AjaxResult sportsClubNameDupl(@RequestBody SportsClub sportsClub, HttpServletRequest request, AjaxResult ajaxResult) {
		if (!sportsClubService.isSportsClubNameUnique(sportsClub)) {
			ajaxResult.setResult("fail");
			return ajaxResult;
		}
		ajaxResult.setResult("success");
		return ajaxResult;
	}
	
	@RequestMapping(value = { "/insert/duplicate/tel-json" }, method = RequestMethod.POST)
	public AjaxResult sportsClubTelDupl(@RequestBody SportsClub sportsClub, HttpServletRequest request, AjaxResult ajaxResult) {
		if (!sportsClubService.isSportsClubTelUnique(sportsClub)) {
			ajaxResult.setResult("fail");
			return ajaxResult;
		}
		ajaxResult.setResult("success");
		return ajaxResult;
	}
}