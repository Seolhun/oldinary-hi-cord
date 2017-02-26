package com.hi.cord.first.stadium;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hi.cord.common.service.CommonService;
import com.hi.cord.first.stadium.service.StadiumService;

@Controller
@RequestMapping("/stadium")
public class StadiumController {
	static final Logger log = LoggerFactory.getLogger(StadiumController.class);

	@Autowired
	private StadiumService stadiumService;
	@Autowired
	private CommonService cFn;

	/**
	 * 스타디움 페이지 이동.
	 * 
	 * @param String
	 * @return String - view
	 * @throws Exception
	 */
	public String moveStadiumPage() throws Exception{
		
		return "page/stadium";
	}
}