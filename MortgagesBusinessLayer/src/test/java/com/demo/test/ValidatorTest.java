package com.demo.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;

import java.text.ParseException;
import java.util.Date;

import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.demo.CustomTest;
import com.mortgage.businesslayer.demo.dto.MortgageDto;
import com.mortgage.businesslayer.demo.exception.MortgageBusinessException;
import com.mortgage.businesslayer.demo.service.impl.MortgageServiceIMPL;
import com.mortgage.businesslayer.demo.validator.offerdate.OfferDateValidator;
import com.mortgage.businesslayer.demo.validator.version.VersionValidator;

public class ValidatorTest extends CustomTest {

	@InjectMocks
	private OfferDateValidator offerDateValidator;

	@InjectMocks
	private VersionValidator versionValidator;

	@Mock
	MortgageServiceIMPL service;

	@Mock
	ConstraintValidatorContext context;

	@Mock
	ConstraintViolationBuilder builder;

	@Before
	public void setup() {
		context = Mockito.mock(ConstraintValidatorContext.class);
		builder = Mockito.mock(ConstraintValidatorContext.ConstraintViolationBuilder.class);
		Mockito.when(context.buildConstraintViolationWithTemplate(Mockito.anyString())).thenReturn(builder);
	}

	@Test
	public void whenOfferDategreaterThansixmonthsfutureDateTest() throws ParseException {
		offerDateValidator.initialize(null);
		assertTrue(isValid(Date_FORMAT.parse("20/05/2021")));
	}

	@Test
	public void whenOfferDatelessthansixmonthsfutureDateTest() throws ParseException {
		assertFalse(isValid(Date_FORMAT.parse("20/05/2015")));
	}

	@Test
	public void isValid_VersionGreaterthanCurrent() throws ParseException, MortgageBusinessException {
		versionValidator.setService(service);
		Mockito.when(service.getMaxVersion(any(String.class))).thenReturn(3);
		MortgageDto mortgageDto = new MortgageDto();
		mortgageDto.setMortgageIDReq("M9");
		mortgageDto.setOfferDateReq(Date_FORMAT.parse("14/03/2021"));
		mortgageDto.setProductIDReq("B1");
		mortgageDto.setOfferIDReq("OI-1");
		mortgageDto.setVersionReq(5);
		assertTrue(isVersionValid(mortgageDto));
	}

	@Test
	public void isValid_VersionLessthancurrent() throws ParseException, MortgageBusinessException {
		versionValidator.setService(service);
		Mockito.when(service.getMaxVersion(any(String.class))).thenReturn(3);
		MortgageDto mortgageDto = new MortgageDto();
		mortgageDto.setMortgageIDReq("M9");
		mortgageDto.setOfferDateReq(Date_FORMAT.parse("14/03/2021"));
		mortgageDto.setProductIDReq("B1");
		mortgageDto.setOfferIDReq("OI-1");
		mortgageDto.setVersionReq(1);
		assertFalse(isVersionValid(mortgageDto));
	}


	private boolean isValid(Date value) {
		return offerDateValidator.isValid(value, context);
	}

	private boolean isVersionValid(MortgageDto mortgageDto) {
		return versionValidator.isValid(mortgageDto, context);
	}
}
