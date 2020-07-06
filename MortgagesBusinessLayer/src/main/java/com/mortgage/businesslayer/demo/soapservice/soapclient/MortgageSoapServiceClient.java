package com.mortgage.businesslayer.demo.soapservice.soapclient;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import com.generated.CreateMortgageRequest;
import com.generated.CreateMortgageResponse;
import com.generated.GetMaxVersionByMortgageIDRequest;
import com.generated.GetMaxVersionByMortgageIDResponse;
import com.generated.GetMortgagesRequest;
import com.generated.GetMortgagesResponse;

public class MortgageSoapServiceClient extends WebServiceGatewaySupport {

	public GetMaxVersionByMortgageIDResponse getmaxVersionByMorgageIDSoapCall(final GetMaxVersionByMortgageIDRequest maxVersionRequest) {
		GetMaxVersionByMortgageIDResponse response = (GetMaxVersionByMortgageIDResponse) getWebServiceTemplate()
				.marshalSendAndReceive(maxVersionRequest);
		return response;
	}

	public GetMortgagesResponse getAllMortgagesSoapCall(final GetMortgagesRequest getMortgagesRequest) {
		final GetMortgagesResponse response = (GetMortgagesResponse) getWebServiceTemplate()
				.marshalSendAndReceive(getMortgagesRequest);
		return response;
	}

	public CreateMortgageResponse createMortgageSoapCall(final CreateMortgageRequest createMortgageRequest) {
		CreateMortgageResponse response = (CreateMortgageResponse) getWebServiceTemplate()
				.marshalSendAndReceive(createMortgageRequest);
		return response;
	}

}
