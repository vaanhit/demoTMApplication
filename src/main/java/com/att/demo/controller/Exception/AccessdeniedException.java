package com.att.demo.controller.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Authenticated user is not authorized") // 404 //
public class AccessdeniedException {
	
	private static final long serialVersionUID = -3332292346834265371L;

	public AccessdeniedException(int id) {
		// super("Authenticated user is not authorized with id=" + id);
		super();
	}

}
