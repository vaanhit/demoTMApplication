package com.att.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.att.demo.entity.Contact;

/**
 * @author Rohit
 *
 */
@Controller
@RequestMapping("/contacts")
public class ContactController {

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping
	public String contacts(Model model) {
		// List<Contact> lst = contactService.findAll();

		List<Contact> contacts = getContacts();

		model.addAttribute("contacts", contacts);
		return "contacts";
	}

	/**
	 * @return Dummy data for testing.
	 */
	private List<Contact> getContacts() {

		List<Contact> contacts = new ArrayList<Contact>();

		contacts.add(new Contact("F1", "L1", new Date(), 1111, "Street 1", "City 1", "state 1", 222221));
		contacts.add(new Contact("F2", "L2", new Date(), 1112, "Street 2", "City 2", "state 2", 222222));
		contacts.add(new Contact("F3", "L3", new Date(), 1113, "Street 3", "City 3", "state 3", 222223));
		contacts.add(new Contact("F4", "L4", new Date(), 1114, "Street 4", "City 4", "state 4", 222224));

		return contacts;
	}

}
