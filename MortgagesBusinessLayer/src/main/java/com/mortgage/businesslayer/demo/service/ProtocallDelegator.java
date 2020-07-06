package com.mortgage.businesslayer.demo.service;

import com.mortgage.businesslayer.demo.dto.GetAllMortgagesConsumerResponse;
import com.mortgage.businesslayer.demo.dto.MortgageDto;

public interface ProtocallDelegator {

	public Integer getMaxVersionByMortgageID(final String mortgageID);

	public GetAllMortgagesConsumerResponse getAllMortgages(final String sortOrder);

	public String createMorgage(final MortgageDto reqEntity);

}
