package com.demo.test;

import static org.mockito.Mockito.when;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.demo.CustomTest;
import com.mortgage.businesslayer.demo.aop.CreateMortgageAspect;
import com.mortgage.businesslayer.demo.aop.MethodExecutionCalculationAspect;
import com.mortgage.businesslayer.demo.dto.MortgageDto;
import com.mortgage.businesslayer.demo.soapservice.handler.BackendSOAPServiceCallDelegator;
import static org.mockito.ArgumentMatchers.any;

public class AspectTest extends CustomTest {

	@Mock
	private ProceedingJoinPoint pjp;
	@Mock
	Signature singnature;
	@Mock
	MethodExecutionCalculationAspect target;
	@InjectMocks
	MethodExecutionCalculationAspect aspect;

	@InjectMocks
	CreateMortgageAspect createMortgageAspect;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Mock
	private BackendSOAPServiceCallDelegator backendSOAPServiceCallDelegator;

	@Test(expected = Test.None.class) /* no exception expected */
	public void testAdvice() throws Throwable {
		when(pjp.proceed()).thenReturn("String");
		when(pjp.getSignature()).thenReturn(singnature);
		when(singnature.getName()).thenReturn("method");
		aspect.around(pjp);
	}

	@Test(expected = Test.None.class) /* no exception expected */
	public void testCreateMortgages() throws Throwable {
		Mockito.when(backendSOAPServiceCallDelegator.createMorgage(any(MortgageDto.class))).thenReturn("Success");
		MortgageDto[] reqEntity= {new MortgageDto()};
		when(pjp.proceed()).thenReturn("String");
		when(pjp.getArgs()).thenReturn(reqEntity);
		when(pjp.getSignature()).thenReturn(singnature);
		when(singnature.getName()).thenReturn("method");
		createMortgageAspect.around(pjp);
	}
}
