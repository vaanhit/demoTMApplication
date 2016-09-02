package com.att.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.att.demo.entity.Contact;

/**
 * @author Rohit
 *
 */
public interface ContactRepository extends JpaRepository<Contact, Integer> {

}
