package com.mortgage.datalayer.demo.restservice.resquesthandler;

import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.generated.GetMaxVersionByMortgageIDResponse;
import com.mortgage.datalayer.demo.dto.CreateMortgageRestRequest;
import com.mortgage.datalayer.demo.dto.GetMortgagesRestResponse;
import com.mortgage.datalayer.demo.dto.MortgageDto;
import com.mortgage.datalayer.demo.model.MortgageEntity;
import com.mortgage.datalayer.demo.restservice.mapper.MortgageRestRequestMapper;
import com.mortgage.datalayer.demo.restservice.mapper.MortgageRestResponseMapper;
import com.mortgage.datalayer.demo.service.MortgageDataLayerService;

@Component
public class MortgageRestRequestHandler {

	private static final Logger log = LoggerFactory.getLogger(MortgageRestRequestHandler.class);
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Autowired
	private MortgageDataLayerService service;

	/**
	 * This method gets mortgages list from datalayer and convert into SOAP Response
	 * 
	 * @param sortOrder
	 * @return GetMortgagesResponse
	 */
	public GetMortgagesRestResponse getAllMortgages(final String sortOrder) {
		final List<MortgageDto> mortgageEntityDtoList = service.getAllMortgages(sortOrder);
		return new MortgageRestResponseMapper().mapGetMortgagesResponse(mortgageEntityDtoList);
	}

	/**
	 * This method helps to convert SOAP request into DTO and pass information to
	 * data layer for persistence purpose
	 * 
	 * @param CreateMortgageRequest createMortgageRequest
	 * @return CreateMortgageResponse
	 */
	public String createMortgage(final CreateMortgageRestRequest createMortgageRestRequest) {
		final MortgageDto reqEntity = new MortgageRestRequestMapper().mapRestuestToDTO(createMortgageRestRequest);
		return  service.createMortgage(reqEntity);
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
