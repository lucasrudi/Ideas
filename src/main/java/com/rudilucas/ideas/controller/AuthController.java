package com.rudilucas.ideas.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller(value="oldAuth")
@RequestMapping(value = "/")
public class AuthController {

	private static final String LOGIN_FORM_URL = "/loginold";

	@RequestMapping(method = RequestMethod.GET, value = "")
	public ModelAndView home(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView("redirect:ideas/getAll");
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET, value = LOGIN_FORM_URL)
	public ModelAndView getLoginForm(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView("login");
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST, value = LOGIN_FORM_URL)
	public ModelAndView doLoging(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView("index");

		return mav;
	}

}
