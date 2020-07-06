package com.mortgage.businesslayer.demo.service;

import com.mortgage.businesslayer.demo.dto.GetAllMortgagesConsumerResponse;
import com.mortgage.businesslayer.demo.dto.MortgageDto;

public interface MortgageService {

	public GetAllMortgagesConsumerResponse getAllMortgages(final String sortOrder);

	public String createMortgage(MortgageDto reqEntity);

	public Integer getMaxVersion(String mortgageID);
}
