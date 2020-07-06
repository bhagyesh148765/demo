package com.mortgage.businesslayer.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Aspect
@Configuration
@EnableAspectJAutoProxy
public class MethodExecutionCalculationAspect {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Around("@annotation( com.mortgage.businesslayer.demo.aop.TrackTime)")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		String METHOD_CLASS_NAME="method " +joinPoint.getSignature().getName();
		logger.info("Entering into " +METHOD_CLASS_NAME);
		long startTime = System.currentTimeMillis();
		Object obj = joinPoint.proceed();
		long timeTaken = System.currentTimeMillis() - startTime;
		logger.info("toal  MilliSecond Time Taken by {} is {}",
				METHOD_CLASS_NAME,
				timeTaken);
		logger.info("Exiting from " + METHOD_CLASS_NAME);
		return obj;
	}
}