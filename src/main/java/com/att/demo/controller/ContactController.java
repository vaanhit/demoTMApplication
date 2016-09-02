package com.att.demo.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.att.demo.entity.Contact;
import com.att.demo.service.ContactService;

/**
 * @author Rohit
 *
 */
@Controller
@RequestMapping("/contacts")
public class ContactController {

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
		// List<Contact> lst = contactService.findAll();

		// List<Contact> con = getContacts();
		List<Contact> con = contactService.getContact(principal.getName());
		model.addAttribute("contacts", con);
		return "contacts";
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/remove/{id}")
	public String removeContact(@PathVariable int id, Model model) {
		contactService.removeContact(id);
		return "redirect:/contacts.html";
	}

	/**
	 * @param model
	 * @param contact
	 * @param result
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/addContact", method = RequestMethod.POST)
	public String saveContact(Model model, @Valid @ModelAttribute("contact") Contact contact, BindingResult result,
			Principal principal) {

		String Currentname = principal.getName();

		Contact con = contactService.saveContact(contact, Currentname);
		// List<Contact> con = addContacts(contact, Currentname);
		model.addAttribute("contact", con);
		return "redirect:/contacts.html";
	}

}
