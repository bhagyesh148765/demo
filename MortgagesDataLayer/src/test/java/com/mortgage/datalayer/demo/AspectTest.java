package com.mortgage.datalayer.demo;

import static org.mockito.Mockito.when;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mortgage.datalayer.demo.aop.MethodExecutionCalculationAspect;

public class AspectTest extends CustomTest {

	@Mock
	private ProceedingJoinPoint pjp;
	@Mock
	Signature singnature;
	@InjectMocks
	MethodExecutionCalculationAspect aspect;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}


	@Test(expected = Test.None.class) /* no exception expected */
	public void testAdvice() throws Throwable {
		when(pjp.proceed()).thenReturn("String");
		when(pjp.getSignature()).thenReturn(singnature);
		when(singnature.getName()).thenReturn("method");
		aspect.around(pjp);
	}

}
