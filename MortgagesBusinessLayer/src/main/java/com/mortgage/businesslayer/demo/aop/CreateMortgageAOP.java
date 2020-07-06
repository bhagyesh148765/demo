/*
 * package com.mortgage.businesslayer.demo.aop;
 * 
 * 
 * import org.aspectj.lang.ProceedingJoinPoint; import
 * org.aspectj.lang.annotation.Around; import
 * org.aspectj.lang.annotation.Aspect; import org.slf4j.Logger; import
 * org.slf4j.LoggerFactory; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.context.annotation.Configuration;
 * 
 * @Aspect
 * 
 * @Configuration public class CreateMortgageAOP {
 * 
 * @Autowired
 * 
 * private Logger logger = LoggerFactory.getLogger(this.getClass());
 * 
 * @Around("@annotation( com.mortgage.datalayer.demo.aop.TrackTime)") public
 * void around(ProceedingJoinPoint joinPoint) throws Throwable {
 * 
 * } }
 */