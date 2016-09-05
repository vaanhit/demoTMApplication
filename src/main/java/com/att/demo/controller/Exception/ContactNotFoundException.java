package com.att.demo.controller.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Rohit
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Contact Not Found") // 404
public class ContactNotFoundException extends Exception {

	private static final long serialVersionUID = -3332292346834265371L;

	/**
	 * @param id
	 */
	public ContactNotFoundException(int id) {
		super("EmployeeNotFoundException with id=" + id);
	}
	
	public ContactNotFoundException() {
		
	}

}
