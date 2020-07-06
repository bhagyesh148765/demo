package com.mortgage.businesslayer.demo.service;

import com.mortgage.businesslayer.demo.dto.GetAllMortgagesConsumerResponse;
import com.mortgage.businesslayer.demo.dto.MortgageDto;
import com.mortgage.businesslayer.demo.exception.MortgageBusinessException;

public interface ProtocallDelegator {

	public Integer getMaxVersionByMortgageID(final String mortgageID) throws MortgageBusinessException;

	public GetAllMortgagesConsumerResponse getAllMortgages(final String sortOrder)  throws MortgageBusinessException;

	public String createMorgage(final MortgageDto reqEntity)  throws MortgageBusinessException;

}
