package com.att.demo.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.att.demo.entity.Contact;
import com.att.demo.repository.ContactRepository;

/**
 * @author Rohit
 *
 */
public class UniqueSSNValidator implements ConstraintValidator<UniqueSSN, Long> {

	@Autowired
	private ContactRepository contactRepository;

	public UniqueSSNValidator() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize(UniqueSSN constraintAnnotation) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isValid(Long ssn, ConstraintValidatorContext context) {
		if (contactRepository == null) {
			return true;
		}
		return contactRepository.findByssn(ssn) == null;
	}

}
