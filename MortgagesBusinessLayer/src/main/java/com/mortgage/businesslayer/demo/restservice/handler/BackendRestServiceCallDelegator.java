package com.mortgage.businesslayer.demo.restservice.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.generated.GetMaxVersionByMortgageIDRequest;
import com.generated.GetMortgagesRequest;
import com.mortgage.businesslayer.demo.aop.TrackTime;
import com.mortgage.businesslayer.demo.dto.GetAllMortgagesBkndRestResponse;
import com.mortgage.businesslayer.demo.dto.GetAllMortgagesConsumerResponse;
import com.mortgage.businesslayer.demo.dto.MortgageDto;
import com.mortgage.businesslayer.demo.exception.MortgageBusinessException;
import com.mortgage.businesslayer.demo.restservice.client.MortgageRestServiceClient;
import com.mortgage.businesslayer.demo.restservice.mapper.MortgageRestResponseMapper;
import com.mortgage.businesslayer.demo.service.ProtocallDelegator;

@Component("REST_SERVICE")
@Qualifier("REST_SERVICE")
public class BackendRestServiceCallDelegator implements ProtocallDelegator {

	@Autowired
	private MortgageRestServiceClient restServiceClient;

	@TrackTime
	public Integer getMaxVersionByMortgageID(final String mortgageID) throws MortgageBusinessException {
		final GetMaxVersionByMortgageIDRequest maxVersionRequest = new GetMaxVersionByMortgageIDRequest();
		maxVersionRequest.setMortgageIDReq(mortgageID);
		return restServiceClient.getmaxVersionByMorgageIDRestCall(mortgageID);
	}

	@TrackTime
	public GetAllMortgagesConsumerResponse getAllMortgages(final String sortOrder) throws MortgageBusinessException {
		final GetMortgagesRequest getMortgagesRequest = new GetMortgagesRequest();
		getMortgagesRequest.setOrderBy(sortOrder);
		final GetAllMortgagesBkndRestResponse response = restServiceClient.getAllMortgagesRestCall(sortOrder);
		return new MortgageRestResponseMapper().mapRestResponse(response);
	}

	@TrackTime
	public String createMorgage(final MortgageDto reqEntity) throws MortgageBusinessException {
		return restServiceClient.createMortgageRestCall(reqEntity);
	}

}
