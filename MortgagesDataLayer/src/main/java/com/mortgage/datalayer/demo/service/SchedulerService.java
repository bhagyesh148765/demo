package com.mortgage.datalayer.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mortgage.datalayer.demo.repository.MortgageRepositoryDao;

@Service
public class SchedulerService {

	@Autowired
	MortgageRepositoryDao repository;

	public void updateExpiryFlag() {
		repository.updateExpiryFlag();
	}

}
