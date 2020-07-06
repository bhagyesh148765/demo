package com.mortgage.datalayer.demo.soapservice.resquesthandler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.generated.CreateMortgageRequest;
import com.generated.CreateMortgageResponse;
import com.generated.GetMaxVersionByMortgageIDResponse;
import com.generated.GetMortgagesResponse;
import com.mortgage.datalayer.demo.dto.MortgageDto;
import com.mortgage.datalayer.demo.service.MortgageDataLayerService;
import com.mortgage.datalayer.demo.soapservice.mapper.MortgageSOAPRequestMapper;
import com.mortgage.datalayer.demo.soapservice.mapper.MortgageSOAPResponseMapper;

@Component
public class MortgageSOAPRequestHandler {

	private static final Logger log = LoggerFactory.getLogger(MortgageSOAPRequestHandler.class);

	@Autowired
	private MortgageDataLayerService service;

	/**
	 * This method gets mortgages list from datalayer and convert into SOAP Response
	 * 
	 * @param sortOrder
	 * @return GetMortgagesResponse
	 */
	public GetMortgagesResponse getAllMortgages(final String sortOrder) {
		List<MortgageDto> mortgageEntityList = service.getAllMortgages(sortOrder);
		return new MortgageSOAPResponseMapper().mapGetMortgagesResponse(mortgageEntityList);
	}

	/**
	 * This method helps to convert SOAP request into DTO and pass information to
	 * data layer for persistence purpose
	 * 
	 * @param CreateMortgageRequest createMortgageRequest
	 * @return CreateMortgageResponse
	 */
	public CreateMortgageResponse createMortgage(final CreateMortgageRequest createMortgageRequest) {
		final MortgageDto reqEntity = new MortgageSOAPRequestMapper().mapSOAPRequestToDTO(createMortgageRequest);
		service.createMortgage(reqEntity);
		CreateMortgageResponse createMortgageResponse = new CreateMortgageResponse();
		createMortgageResponse.setStatus("Success");
		return createMortgageResponse;
	}

	/**
	 * This method gets max version for particular mortgageID
	 * 
	 * @param mortgageID
	 * @return
	 */
	public GetMaxVersionByMortgageIDResponse getMaxVersionByMortgageID(final String mortgageID) {
		final Integer maxVersion = service.getMaxVersionByMortgageID(mortgageID);
		GetMaxVersionByMortgageIDResponse response = new GetMaxVersionByMortgageIDResponse();
		response.setMaxVersion(maxVersion);
		return response;
	}

}
