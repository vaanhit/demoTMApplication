package com.att.demo.service;

import java.util.List;

import com.att.demo.entity.Contact;

/**
 * @author Rohit
 *
 */
public interface ContactService {

	/**
	 * @param contact
	 * @return
	 */
	public Contact saveContact(Contact contact);

	/**
	 * @return
	 */
	public List<Contact> getContact(String userName);

	/**
	 * @return
	 */
	public void removeContact(Contact con);

	/**
	 * @param ids
	 */
	public void removeeContacts(String ids);

	/**
	 * @param id
	 * @return
	 */
	public Contact findOne(int id);

	/**
	 * @param ssn
	 * @return
	 */
	public Contact findOneByssn(Long ssn);

}
