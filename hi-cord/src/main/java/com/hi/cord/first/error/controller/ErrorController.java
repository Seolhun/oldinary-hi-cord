package com.hi.cord.first.error.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/common/error")
public class ErrorController {

	@RequestMapping(value = "/exception")
	public String exception(ModelMap model, HttpServletRequest request) {
		model.addAttribute("msg", "alert.error.exception");
		return "error/common";
	}

	@RequestMapping(value = "/400")
	public String pageError400(ModelMap model, HttpServletRequest request) {
		model.addAttribute("error", "400");
		model.addAttribute("msg", "alert.error.400");
		return "error/common";
	}

	@RequestMapping(value = "/403")
	public String pageError403(ModelMap model, HttpServletRequest request) {
		model.addAttribute("error", "403");
		model.addAttribute("msg", "alert.error.403");
		return "error/common";
	}

	@RequestMapping(value = "/404")
	public String pageError404(ModelMap model, HttpServletRequest request) {
		model.addAttribute("error", "404");
		model.addAttribute("msg", "alert.error.404");
		model.addAttribute("exMsg", "alert.error.404.txt2");
		return "error/common";
	}

	@RequestMapping(value = "/405")
	public String pageError405(ModelMap model, HttpServletRequest request) {
		model.addAttribute("error", "405");
		model.addAttribute("msg", "alert.error.405");
		return "error/common";
	}

	@RequestMapping(value = "/500")
	public String pageError500(ModelMap model, HttpServletRequest request) {
		model.addAttribute("error", "500");
		model.addAttribute("msg", "alert.error.500");
		model.addAttribute("msgSub", "alert.error.500.txt2");
		return "error/common";
	}

	@RequestMapping(value = "/503")
	public String pageError503(ModelMap model, HttpServletRequest request) {
		model.addAttribute("error", "503");
		model.addAttribute("msg", "alert.error.503");
		return "error/common";
	}
}