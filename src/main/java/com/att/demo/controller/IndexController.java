package com.att.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Rohit
 *
 */
@Controller
public class IndexController {

	@RequestMapping("/index")
	public String index(Model model) {
		System.out.println("Inside Controller");

		return "index";
	}
}
