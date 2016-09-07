package com.att.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.att.demo.entity.Contact;

/**
 * @author Rohit
 *
 */
public interface ContactRepository extends JpaRepository<Contact, Integer> {

	/**
	 * @param userName
	 * @return This is not in use but can get called from spring security
	 */
	@Query(nativeQuery = true, value = "select * from contact where ssn= ? ")
	public Contact findByssn(Long ssn);
}
