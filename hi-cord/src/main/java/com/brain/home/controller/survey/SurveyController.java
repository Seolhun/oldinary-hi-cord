package com.brain.home.controller.survey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.brain.home.entity.survey.Survey;

@Controller
@RequestMapping(value = "/survey")
public class SurveyController {
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String indexPage(HttpServletRequest request, HttpServletResponse response) {
		return "views/survey/list";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String surveyAddGet(Model model,HttpServletRequest request, HttpServletResponse response) {
		model.addAttribute("survey", new Survey());
		return "views/survey/add";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String surveyAddPost(HttpServletRequest request, HttpServletResponse response) {
		return "redirect:/survey/list";
	}
}