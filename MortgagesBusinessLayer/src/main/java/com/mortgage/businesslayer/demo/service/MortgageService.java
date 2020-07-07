package com.mortgage.businesslayer.demo.service;

import com.generated.CreateMortgageResponse;
import com.mortgage.businesslayer.demo.dto.GetAllMortgagesConsumerResponse;
import com.mortgage.businesslayer.demo.dto.MortgageDto;
import com.mortgage.businesslayer.demo.exception.MortgageBusinessException;

public interface MortgageService {

	public GetAllMortgagesConsumerResponse getAllMortgages(final String sortOrder) throws MortgageBusinessException;

	public CreateMortgageResponse createMortgage(MortgageDto reqEntity)  throws MortgageBusinessException;

	public Integer getMaxVersion(String mortgageID)   throws MortgageBusinessException;
}
