package com.hi.cord.first.stadium;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hi.cord.common.model.AjaxResult;
import com.hi.cord.common.service.CommonService;
import com.hi.cord.first.stadium.model.Stadium;
import com.hi.cord.first.stadium.service.StadiumService;

@RestController
@RequestMapping("/stadium")
public class StadiumRestController {
	static final Logger log = LoggerFactory.getLogger(StadiumRestController.class);

	@Autowired
	private StadiumService stadiumService;
	@Autowired
	private CommonService cFn;

	/**
	 * 스타디움 인서트
	 * 
	 * @param Stadium
	 * @return AjaxResult
	 * @throws Exception
	 */
	@RequestMapping(value = { "/insert" }, method = RequestMethod.POST)
	public AjaxResult insertStadium(@Valid Stadium stadium, ModelMap model, AjaxResult ajaxResult) throws Exception{
		ajaxResult.setResult("fail");
		
		
		return ajaxResult;
	}
	
	/**
	 * 스타디움 Select
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
	 * 스타디움 SelectList
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
	 * 스타디움 Update
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
	 * 스타디움 delete
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
}