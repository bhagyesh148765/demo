
package com.mortgage.businesslayer.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import com.mortgage.businesslayer.demo.dto.MortgageDto;
import com.mortgage.businesslayer.demo.service.ProtocallDelegator;

@Aspect

@Configuration
public class CreateMortgageAspect {

	@Value("${protocall}")
	private String protocall;

	private ProtocallDelegator protocallDelegator;

	@Autowired
	public void setProtocallDelegator(ApplicationContext context) {
		protocallDelegator = (ProtocallDelegator) context.getBean(protocall);
	}

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Around("@annotation(com.mortgage.businesslayer.demo.aop.CreateMortgage)")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

		String METHOD_CLASS_NAME = "method " + joinPoint.getSignature().getName();
		logger.info("Entering into " + METHOD_CLASS_NAME);
		long startTime = System.currentTimeMillis();
		MortgageDto reqEntity=(MortgageDto) joinPoint.getArgs()[0];
		protocallDelegator.createMorgage(reqEntity);
		Object obj = joinPoint.proceed();
		long timeTaken = System.currentTimeMillis() - startTime;
		logger.info("toal  MilliSecond Time Taken by {} is {}", METHOD_CLASS_NAME, timeTaken);
		logger.info("Exiting from " + METHOD_CLASS_NAME);
		return obj;
	}
}
