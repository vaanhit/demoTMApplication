package com.att.demo.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.data.domain.Sort.Direction;

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
	
	 @PersistenceContext
	 private EntityManager manager;

	public ContactServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.att.demo.service.ContactService#saveContact(com.att.demo.entity.Contact, java.lang.String)
	 */
	@Override
	@Transactional
	@PreAuthorize("#contact.userName == authentication.name or hasRole('ROLE_ADMIN')")
	public Contact saveContact(@P("contact") Contact contact) {
		return contactRepository.save(contact);
	}

	/* (non-Javadoc)
	 * @see com.att.demo.service.ContactService#getContact(java.lang.String)
	 */
	@Override
	@Transactional
	public List<Contact> getContact(String userName) {
		List<Contact> con = contactRepository.findAll();
		
		//Find By username with native query. 
		//List<Contact> con = contactRepository.getAllContacts("admin");
		
		//Pagination from server side.
		//List<Contact> con = contactRepository.findAll(new PageRequest(0, 4, Direction.DESC, "userName")).getContent();

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
	
	@Override
	public Contact findOneByssn(Long ssn) {
		return contactRepository.findByssn(ssn);
	}

	/* (non-Javadoc)
	 * @see com.att.demo.service.ContactService#removeeContacts(java.lang.String)
	 * 
	 * Implement JPQL for demo.
	 */
	@Override
	@Transactional
    @Rollback(true)
	public void removeeContacts(String ids) {
		try {
			Query query = manager.createNativeQuery("DELETE FROM CONTACT WHERE ID in (" + ids + ")");
			query.executeUpdate();
		} catch(Exception exp) {
			exp.printStackTrace();
		}
	}

}
