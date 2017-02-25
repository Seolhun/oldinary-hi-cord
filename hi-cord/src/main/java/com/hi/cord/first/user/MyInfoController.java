package com.hi.cord.first.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Transactional(propagation = Propagation.REQUIRED, transactionManager = "txManager", noRollbackFor = {NullPointerException.class })
public class MyInfoController {

	private static final Logger log = LoggerFactory.getLogger(MyInfoController.class);

	@RequestMapping(value = "/myinfo", method = RequestMethod.GET)
	public String myInfoPage(ModelMap model) throws Exception {
		log.info("myInfoPage");
		return "views/myinfo/profile";
	}
}