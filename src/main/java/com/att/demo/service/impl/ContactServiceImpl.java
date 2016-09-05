package com.att.demo.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.att.demo.entity.Contact;
import com.att.demo.repository.ContactRepository;
import com.att.demo.service.ContactService;

/**
 * @author Rohit
 *
 */
@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactRepository contactRepository;

	public ContactServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.att.demo.service.ContactService#saveContact(com.att.demo.entity.Contact, java.lang.String)
	 */
	@Override
	@Transactional
	@PreAuthorize("#contact.userName == authentication.name or hasRole('ROLE_ADMIN')")
	public Contact saveContact(@P("contact") Contact contact, String userName) {
		contact.setUserName(userName);
		
		return contactRepository.save(contact);
	}

	/* (non-Javadoc)
	 * @see com.att.demo.service.ContactService#getContact(java.lang.String)
	 */
	@Override
	@Transactional
	public List<Contact> getContact(String userName) {
		List<Contact> con = contactRepository.findAll();

		return con;
	}

	/* (non-Javadoc)
	 * @see com.att.demo.service.ContactService#removeContact(java.lang.Integer)
	 */
	@Override
	@Transactional
	@PreAuthorize("#contact.userName == authentication.name or hasRole('ROLE_ADMIN')")
	public void removeContact(@P("contact") Contact contact) {
		contactRepository.delete(contact);
	}
	
	@Override
	public Contact findOne(int id) {
		return contactRepository.findOne(id);
	}

}
