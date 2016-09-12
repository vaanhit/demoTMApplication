package com.att.reporting.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.att.demo.entity.Contact;
import com.att.demo.service.ContactService;

/**
 * @author Rohit
 *
 */
@Controller
public class ReportingController {

	@Autowired
	ContactService contactService;

	public ReportingController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return
	 */
	@RequestMapping(value = "/contactExport", method = RequestMethod.GET)
	public ModelAndView getExcel() {

		List<Contact> contactList = contactService.getContact("");
		return new ModelAndView("contactListExcel", "contactList", contactList);
	}

}
