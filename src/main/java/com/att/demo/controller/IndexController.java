package com.att.demo.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Rohit
 *
 */
@Controller
public class IndexController {
	private final static Logger logger = Logger.getLogger(IndexController.class);

	@RequestMapping("/index")
	public String index(Model model) {
		logger.info("Inside Controller");

		return "index";
	}
}
