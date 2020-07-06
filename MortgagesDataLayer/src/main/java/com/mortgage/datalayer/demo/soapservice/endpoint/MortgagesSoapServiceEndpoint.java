package com.mortgage.datalayer.demo.soapservice.endpoint;

import javax.xml.datatype.DatatypeConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import com.generated.CreateMortgageRequest;
import com.generated.CreateMortgageResponse;
import com.generated.GetMaxVersionByMortgageIDRequest;
import com.generated.GetMaxVersionByMortgageIDResponse;
import com.generated.GetMortgagesRequest;
import com.generated.GetMortgagesResponse;
import com.mortgage.datalayer.demo.soapservice.resquesthandler.MortgageSOAPRequestHandler;

@Endpoint
public class MortgagesSoapServiceEndpoint {

	private static final String NAMESPACE_URI = "http://www.barclays.org/mortgage/";

	@Autowired
	private MortgageSOAPRequestHandler mortgageSOAPRequestHandler;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getMortgagesRequest")
	@ResponsePayload
	public GetMortgagesResponse getAllMortgages(@RequestPayload GetMortgagesRequest request)
			throws DatatypeConfigurationException {
		return mortgageSOAPRequestHandler.getAllMortgages(request.getOrderBy());
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getMaxVersionByMortgageIDRequest")
	@ResponsePayload
	public GetMaxVersionByMortgageIDResponse getMaxVersionByMortgageID(
			@RequestPayload GetMaxVersionByMortgageIDRequest request) throws DatatypeConfigurationException {
		return mortgageSOAPRequestHandler.getMaxVersionByMortgageID(request.getMortgageIDReq());
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "createMortgageRequest")
	@ResponsePayload
	public CreateMortgageResponse createMorgage(@RequestPayload CreateMortgageRequest request)
			throws DatatypeConfigurationException {
		return mortgageSOAPRequestHandler.createMortgage(request);
	}
}
