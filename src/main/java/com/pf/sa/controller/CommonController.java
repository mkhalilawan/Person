package com.pf.sa.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class CommonController {
	private Logger logger = LogManager.getLogger(CommonController.class);



	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView dashboardView(HttpServletRequest request, Model model, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("common/main");
		addPreDefinedFields(model);
		return modelAndView;
	}

	@RequestMapping(value = "/login")
	public ModelAndView loginView(HttpServletRequest request, Model model, HttpServletResponse response)
			throws Exception {

		ModelAndView modelAndView = new ModelAndView("login");
		addPreDefinedFields(model);
		return modelAndView;
	}

	private void addPreDefinedFields(Model model) {

		model.addAttribute("navigationMenuRoles", "");
		model.addAttribute("userRole", "ADMIN");
	}

}
