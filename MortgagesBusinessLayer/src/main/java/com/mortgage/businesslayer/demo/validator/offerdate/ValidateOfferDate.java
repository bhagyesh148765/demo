package com.mortgage.businesslayer.demo.validator.offerdate;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Retention(RUNTIME)
@Target({ FIELD, ANNOTATION_TYPE, PARAMETER })
@Constraint(validatedBy = OfferDateValidator.class)
public @interface ValidateOfferDate {

	String message() default "Offered Date is not Valid, it shoudbe greater than current date+ 6months ";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
