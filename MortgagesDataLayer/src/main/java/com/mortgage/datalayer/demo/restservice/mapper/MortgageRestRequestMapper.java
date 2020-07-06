package com.mortgage.datalayer.demo.restservice.mapper;

import com.mortgage.datalayer.demo.dto.CreateMortgageRestRequest;
import com.mortgage.datalayer.demo.dto.MortgageDto;

public class MortgageRestRequestMapper {

	/***
	 * Converts Incoming SOAP uest CreateMortgageuest to MortgageDTO
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
