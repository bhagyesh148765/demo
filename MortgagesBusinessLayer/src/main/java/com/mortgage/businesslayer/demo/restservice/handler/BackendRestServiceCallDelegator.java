package com.mortgage.businesslayer.demo.restservice.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.generated.GetMaxVersionByMortgageIDRequest;
import com.generated.GetMortgagesRequest;
import com.mortgage.businesslayer.demo.dto.GetAllMortgagesBkndRestResponse;
import com.mortgage.businesslayer.demo.dto.GetAllMortgagesConsumerResponse;
import com.mortgage.businesslayer.demo.dto.MortgageDto;
import com.mortgage.businesslayer.demo.restservice.client.MortgageRestServiceClient;
import com.mortgage.businesslayer.demo.restservice.mapper.MortgageRestResponseMapper;
import com.mortgage.businesslayer.demo.service.ProtocallDelegator;

@Component("REST_SERVICE")
@Qualifier("REST_SERVICE")
public class BackendRestServiceCallDelegator implements ProtocallDelegator {

	private static final Logger log = LoggerFactory.getLogger(BackendRestServiceCallDelegator.class);
	@Autowired
	private MortgageRestServiceClient restServiceClient;

	public Integer getMaxVersionByMortgageID(final String mortgageID) {
		final GetMaxVersionByMortgageIDRequest maxVersionRequest = new GetMaxVersionByMortgageIDRequest();
		maxVersionRequest.setMortgageIDReq(mortgageID);
		final Integer max = restServiceClient.getmaxVersionByMorgageIDRestCall(mortgageID);
		return max;
	}

	public GetAllMortgagesConsumerResponse getAllMortgages(final String sortOrder) {
		final GetMortgagesRequest getMortgagesRequest = new GetMortgagesRequest();
		getMortgagesRequest.setOrderBy(sortOrder);
		final GetAllMortgagesBkndRestResponse response = restServiceClient.getAllMortgagesRestCall(sortOrder);
		return new MortgageRestResponseMapper().mapRestResponse(response);
	}

	public String createMorgage(final MortgageDto reqEntity) {
		final String status = restServiceClient.createMortgageRestCall(reqEntity);
		return status;
	}

}
