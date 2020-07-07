package com.mortgage.businesslayer.demo.validator.offerdate;
import java.util.Calendar;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OfferDateValidator implements ConstraintValidator<ValidateOfferDate, Date> {

	@Override
	public void initialize(ValidateOfferDate constraintAnnotation) {

	}

	@Override
	public boolean isValid(Date value, ConstraintValidatorContext context) {

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 6);
		Date sixMonthsDate = cal.getTime();
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(
				"Offer Date Error, Input Offer Date " + value + " is less than six month future Date")
				.addConstraintViolation();
		return sixMonthsDate.before(value);
	}

	/**
	 * 
	 */
	public OfferDateValidator() {
		super();
	}
}
