package com.att.demo.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @author Rohit
 *
 */
@Controller
public class LoginController {
	private final static Logger logger = Logger.getLogger(LoginController.class);

	/**
	 * @return Login page of the application.
	 */
	@RequestMapping("/login")
	public String login() {
		logger.info("Inside Login");
		return "login";
	}
	
	@RequestMapping("/loginError")
	public String loginCheck(Model model,
			@RequestParam(value = "error", required = false) String error) {
		logger.info("Inside Login Check");
		
		if (error != null) {
			model.addAttribute("error", "Invalid username and password!");
		}
		return "login";
	}
}
