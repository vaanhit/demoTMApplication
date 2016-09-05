package com.att.demo.controller;

import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.att.demo.controller.Exception.ContactNotFoundException;
import com.att.demo.controller.Exception.GenricException;
import com.att.demo.entity.Contact;
import com.att.demo.service.ContactService;

/**
 * @author Rohit
 *
 */
@Controller
@RequestMapping("/contacts")
public class ContactController {
	private final static Logger logger = Logger.getLogger(ContactController.class);
	@Autowired
	ContactService contactService;

	/**
	 * @return
	 */
	@ModelAttribute("contact")
	public Contact constructBlog() {
		return new Contact();
	}

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping
	public String contacts(Model model, Principal principal) {
		List<Contact> con = contactService.getContact(principal.getName());
		model.addAttribute("contacts", con);

		logger.info("Get all contacts" + con);

		return "contacts";
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/remove/{id}")
	public String removeContact(@PathVariable int id, Model model) throws Exception {

		// http://localhost:8080/demoTMApplication/contacts/remove/1.html
		try {
			Contact con = contactService.findOne(id);
			contactService.removeContact(con);
		} catch (IllegalArgumentException exp) {
			throw new ContactNotFoundException(id);
		} catch (AccessDeniedException exp) {
			// http://localhost:8080/demoTMApplication/contacts/remove/3.html
			throw new AccessDeniedException("Authenticated user is not authorized");
		} catch (Exception exp) {
			throw new GenricException("Unknown Error Occured, please contact support.");
		}

		return "redirect:/contacts.html";
	}

	/**
	 * @param model
	 * @param contact
	 * @param result
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/addUpdateContact", method = RequestMethod.POST)
	public String saveUpdateContact(Model model, @Valid @ModelAttribute("contact") Contact contact,
			BindingResult result, Principal principal) throws Exception {
		/*
		 * if (result.hasErrors()) { return "redirect:/contacts.html"; }
		 */

		String Currentname = principal.getName();
		Contact con;

		if (Currentname == null) {
			return "login";
		}
		try {
			con = contactService.saveContact(contact, Currentname);
		} catch (IllegalArgumentException exp) {
			throw new ContactNotFoundException();
		} catch (AccessDeniedException exp) {
			// http://localhost:8080/demoTMApplication/contacts/remove/3.html
			throw new AccessDeniedException("Authenticated user is not authorized");
		} catch (Exception exp) {
			throw new GenricException("Unknown Error Occured, please contact support.");
		}

		model.addAttribute("contact", con);
		return "redirect:/contacts.html";
	}

	/************************************************************
	 * ***************** Exception Handler **********************
	 * **********************************************************
	 */
	/**
	 * @param request
	 * @param ex
	 * @param model
	 * @return
	 */
	@ExceptionHandler({ ContactNotFoundException.class, AccessDeniedException.class })
	public String handleMultipleExceptions(HttpServletRequest request, Exception ex, Model model) {
		logger.error("Requested URL=" + request.getRequestURL());
		logger.error("Exception Raised=" + ex);

		model.addAttribute("exception", ex.getMessage());
		// model.addAttribute("url", request.getRequestURL());

		return "error";
	}

}
