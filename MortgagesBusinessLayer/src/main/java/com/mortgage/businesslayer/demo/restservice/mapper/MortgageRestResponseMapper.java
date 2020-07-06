package com.mortgage.businesslayer.demo.restservice.mapper;

import com.mortgage.businesslayer.demo.dto.GetAllMortgagesBkndRestResponse;
import com.mortgage.businesslayer.demo.dto.GetAllMortgagesConsumerResponse;

public class MortgageRestResponseMapper {

	public GetAllMortgagesConsumerResponse mapRestResponse(GetAllMortgagesBkndRestResponse response) {
		// TODO Auto-generated method stub
		GetAllMortgagesConsumerResponse consumerResponseDto = new GetAllMortgagesConsumerResponse();	
		consumerResponseDto.getMortgages().addAll(response.getMortgages());
		return consumerResponseDto;
	}

}
