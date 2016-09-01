package com.att.demo.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


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
		System.out.println("Inside Login");
		return "login";
	}
}
