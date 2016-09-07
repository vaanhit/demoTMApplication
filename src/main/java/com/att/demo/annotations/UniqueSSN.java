package com.att.demo.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * @author Rohit
 *
 */
@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = { UniqueSSNValidator.class })
public @interface UniqueSSN {
	String message();

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
