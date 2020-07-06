package com.mortgage.businesslayer.demo.validator.version;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Retention(RUNTIME)
@Target(TYPE)
@Constraint(validatedBy = VersionValidator.class)
public @interface ValidateVersion {
	String message() default "Version issue ";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
