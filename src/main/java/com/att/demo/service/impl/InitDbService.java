package com.att.demo.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.att.demo.entity.Contact;
import com.att.demo.repository.ContactRepository;

/**
 * @author Monika
 *
 */
@Transactional
@Service
public class InitDbService {

	@Autowired
	private ContactRepository contactRepository;

	/**
	 * This method is for initial operations. This will call after all the beans
	 * got Construct.
	 */
	@PostConstruct
	public void init() {
		if (contactRepository != null) {
			contactRepository.save(new Contact(1, "Adam", "Adam L", new Date(), 1111, "Street 1", "City 1", "state 1", 222221, "rohit"));
			contactRepository.save(new Contact(2, "Rohit", "Rohit L", new Date(), 1112, "Street 2", "City 2", "state 2", 222222, "rohit"));
			contactRepository.save(new Contact(3, "Paul", "Paul L", new Date(), 1113, "Street 3", "City 3", "state 3", 222223, "admin"));
			contactRepository.save(new Contact(4, "Monika", "Monika L", new Date(), 1114, "Street 4", "City 4", "state 4", 222224, "admin"));
			/*contactRepository.save(new Contact(5, "AAA", "AAA L", new Date(), 1115, "Street 5", "City 5", "state 5", 222225, "rohit1"));
			contactRepository.save(new Contact(6, "BBB", "BBB L", new Date(), 1116, "Street 6", "City 6", "state 6", 222226, "rohit2"));
			contactRepository.save(new Contact(7, "CCC", "CCC L", new Date(), 1117, "Street 7", "City 7", "state 7", 222227, "rohit1"));
			contactRepository.save(new Contact(8, "DDD", "DDD L", new Date(), 1118, "Street 8", "City 8", "state 8", 222228, "rohit2"));*/
			
		}
	}
}
