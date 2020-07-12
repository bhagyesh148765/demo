package com.mortgage.businesslayer.demo.soapservice.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.generated.CreateMortgageRequest;
import com.generated.CreateMortgageResponse;
import com.generated.GetMaxVersionByMortgageIDRequest;
import com.generated.GetMaxVersionByMortgageIDResponse;
import com.generated.GetMortgagesRequest;
import com.generated.GetMortgagesResponse;
import com.mortgage.businesslayer.demo.aop.TrackTime;
import com.mortgage.businesslayer.demo.dto.GetAllMortgagesConsumerResponse;
import com.mortgage.businesslayer.demo.dto.MortgageDto;
import com.mortgage.businesslayer.demo.exception.MortgageBusinessException;
import com.mortgage.businesslayer.demo.service.ProtocallDelegator;
import com.mortgage.businesslayer.demo.soapservice.mapper.MortgageSOAPRequestMapper;
import com.mortgage.businesslayer.demo.soapservice.mapper.MortgageSOAPResponseMapper;
import com.mortgage.businesslayer.demo.soapservice.soapclient.MortgageSoapServiceClient;

@Service("SOAP_SERVICE")
@Qualifier("SOAP_SERVICE")
/**
 * This class represents SOAP Gateway for calling SOAP Services 
 * @author bhagyesh
 *
 */
public class BackendSOAPServiceCallDelegator implements ProtocallDelegator {


	@Autowired
	private MortgageSoapServiceClient soapServiceClient;

	/***
	 * This method is used to get max version for given mortgageID by calling
	 * backend SOAP service
	 * @throws MortgageBusinessException 
	 */
	@TrackTime
	public Integer getMaxVersionByMortgageID(final String mortgageID) throws MortgageBusinessException {
		final GetMaxVersionByMortgageIDRequest maxVersionRequest = new GetMaxVersionByMortgageIDRequest();
		maxVersionRequest.setMortgageIDReq(mortgageID);
		final GetMaxVersionByMortgageIDResponse response = soapServiceClient
				.getmaxVersionByMorgageIDSoapCall(maxVersionRequest);
		return response.getMaxVersion();
	}

	/***
	 * This method is used to get all mortgages based on sorting parameter
	 * sortOrder by calling backend SOAP service
	 * @throws MortgageBusinessException 
	 */
	@TrackTime
	public GetAllMortgagesConsumerResponse getAllMortgages(final String sortOrder) throws MortgageBusinessException {
		final GetMortgagesRequest getMortgagesRequest = new GetMortgagesRequest();
		getMortgagesRequest.setOrderBy(sortOrder);
		final GetMortgagesResponse response = soapServiceClient.getAllMortgagesSoapCall(getMortgagesRequest);
		return new MortgageSOAPResponseMapper().mapSOAPResponse(response);
	}

	/***
	 * This method is used to create mortgages by calling backend SOAP service
	 * @throws MortgageBusinessException 
	 */
	@TrackTime
	public String createMorgage(final MortgageDto reqEntity) throws MortgageBusinessException {
		CreateMortgageRequest createMortgageRequest = new MortgageSOAPRequestMapper().mapSOAPRequest(reqEntity);
		CreateMortgageResponse response = soapServiceClient.createMortgageSoapCall(createMortgageRequest);
		return response.getStatus();
	}

	/**
	 * @param soapServiceClient the soapServiceClient to set
	 */
	public void setSoapServiceClient(MortgageSoapServiceClient soapServiceClient) {
		this.soapServiceClient = soapServiceClient;
	}

}
