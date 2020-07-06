package com.mortgage.businesslayer.demo.soapservice.soapclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import com.generated.CreateMortgageRequest;
import com.generated.CreateMortgageResponse;
import com.generated.GetMaxVersionByMortgageIDRequest;
import com.generated.GetMaxVersionByMortgageIDResponse;
import com.generated.GetMortgagesRequest;
import com.generated.GetMortgagesResponse;
import com.mortgage.businesslayer.demo.exception.MortgageBusinessException;
import com.mortgage.businesslayer.demo.restservice.client.MortgageRestServiceClient;

public class MortgageSoapServiceClient extends WebServiceGatewaySupport {

	private static final Logger log = LoggerFactory.getLogger(MortgageSoapServiceClient.class);

	public GetMaxVersionByMortgageIDResponse getmaxVersionByMorgageIDSoapCall(
			final GetMaxVersionByMortgageIDRequest maxVersionRequest) throws MortgageBusinessException {
		log.info("entering into getmaxVersionByMorgageIDSoapCall method ");
		try {
			GetMaxVersionByMortgageIDResponse response = (GetMaxVersionByMortgageIDResponse) getWebServiceTemplate()
					.marshalSendAndReceive(maxVersionRequest);
			log.info("exiting from getmaxVersionByMorgageIDSoapCall method ");
			return response;
		} catch (Exception e) {
			log.error("error occured during backend communication" + e.getMessage() + e.getCause());
			throw new MortgageBusinessException("Communication Error", e, HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	public GetMortgagesResponse getAllMortgagesSoapCall(final GetMortgagesRequest getMortgagesRequest)
			throws MortgageBusinessException {
		try {
			log.info("entering into getAllMortgagesSoapCall method ");
			final GetMortgagesResponse response = (GetMortgagesResponse) getWebServiceTemplate()
					.marshalSendAndReceive(getMortgagesRequest);
			log.info("exiting from getAllMortgagesSoapCall method ");
			return response;
		} catch (Exception e) {
			log.error("error occured during backend communication" + e.getMessage() + e.getCause());
			throw new MortgageBusinessException("Communication Error", e, HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	public CreateMortgageResponse createMortgageSoapCall(final CreateMortgageRequest createMortgageRequest) throws MortgageBusinessException {
		try {
			log.info("entering into createMortgageSoapCall method ");
			CreateMortgageResponse response = (CreateMortgageResponse) getWebServiceTemplate()
					.marshalSendAndReceive(createMortgageRequest);
			log.info("exiting from createMortgageSoapCall method ");
			return response;
		} catch (Exception e) {
			log.error("error occured during backend communication" + e.getMessage() + e.getCause());
			throw new MortgageBusinessException("Communication Error", e, HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

}
