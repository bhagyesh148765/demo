package com.mortgage.datalayer.demo.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mortgage.datalayer.demo.aop.TrackTime;
import com.mortgage.datalayer.demo.service.SchedulerService;

@Component
public class MortgageExpiryUpdateFlagScheduler {

	@Autowired

	private SchedulerService schedulerService;

	private static final Logger log = LoggerFactory.getLogger(MortgageExpiryUpdateFlagScheduler.class);

	@Scheduled(fixedRate = 10000)
	@TrackTime
	public void updateExpiryFlag() {
		schedulerService.updateExpiryFlag();
	}
}
