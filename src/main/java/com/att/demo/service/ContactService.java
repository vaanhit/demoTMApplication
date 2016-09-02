package com.att.demo.service;

import java.util.List;

import com.att.demo.entity.Contact;

/**
 * @author Rohit
 *
 */
public interface ContactService {

	/**
	 * @param item
	 * @param id
	 */
	public Contact saveContact(Contact contact, String userName);

	/**
	 * @return
	 */
	public List<Contact> getContact(String userName);

	/**
	 * @return
	 */
	public void removeContact(Integer contactID);

}
