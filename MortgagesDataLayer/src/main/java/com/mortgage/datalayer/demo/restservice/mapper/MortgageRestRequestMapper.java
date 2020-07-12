package com.mortgage.datalayer.demo.restservice.mapper;

import com.mortgage.datalayer.demo.dto.CreateMortgageRestRequest;
import com.mortgage.datalayer.demo.dto.MortgageDto;

/**
 * Class responsible for creation of database entities from Rest request 
 * 
 * @author bhagyesh
 *
 */
public class MortgageRestRequestMapper {

	/***
	 * Converts Incoming Rest request CreateMortgageuest to database entity  MortgageDTO 
	 * 
	 * @param CreateMortgageuest createMortgageuest
	 * @return MortgageDto
	 */
	public MortgageDto mapRestuestToDTO(final CreateMortgageRestRequest createMortgageRestRequest) {
		final MortgageDto mortgageDto = new MortgageDto();
		mortgageDto.setMortgageID(createMortgageRestRequest.getMortgageIDReq());
		mortgageDto.setVersion(createMortgageRestRequest.getVersionReq());
		mortgageDto.setOfferID(createMortgageRestRequest.getOfferIDReq());
		mortgageDto.setProductID(createMortgageRestRequest.getProductIDReq());
		mortgageDto.setOfferDate(createMortgageRestRequest.getOfferDateReq());
		return mortgageDto;
	}
}
