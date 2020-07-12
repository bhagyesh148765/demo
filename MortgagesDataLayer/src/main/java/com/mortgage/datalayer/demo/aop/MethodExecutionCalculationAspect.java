package com.mortgage.datalayer.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration

/**
 * This class is used to calculate time taken by method to execute business
 * logic This is annotation based invocation, method being specified by
 * TrackTime annotation is eligible for time calculation
 * 
 * @author bhagyesh
 *
 */
public class MethodExecutionCalculationAspect {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * AOP method to calculate time for business execution
	 * 
	 * @param joinPoint
	 * @throws Throwable
	 */

	@Around("@annotation( com.mortgage.datalayer.demo.aop.TrackTime)")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		String METHOD_CLASS_NAME = "method " + joinPoint.getSignature().getName();
		logger.info("Entering into " + METHOD_CLASS_NAME);
		final long startTime = System.currentTimeMillis();
		Object obj = joinPoint.proceed();
		final long timeTaken = System.currentTimeMillis() - startTime;
		logger.info("toal  MilliSecond Time Taken by {} is {}", METHOD_CLASS_NAME, timeTaken);
		logger.info("Exiting from " + METHOD_CLASS_NAME);
		return obj;
	}

}