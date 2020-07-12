package com.mortgage.businesslayer.demo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.generated.CreateMortgageResponse;
import com.mortgage.businesslayer.demo.aop.CreateMortgage;
import com.mortgage.businesslayer.demo.dto.GetAllMortgagesConsumerResponse;
import com.mortgage.businesslayer.demo.dto.MortgageDto;
import com.mortgage.businesslayer.demo.exception.MortgageBusinessException;
import com.mortgage.businesslayer.demo.service.MortgageService;
import com.mortgage.businesslayer.demo.service.ProtocallDelegator;

/**
 * This class represents Service implementation at business layer This class
 * will route the requests to SOAP gateway or Rest gateway based on
 * configauration
 * 
 * @author bhagyesh
 *
 */
@Service
public class MortgageServiceIMPL implements MortgageService {
	private static final String SUCCESS = "Success";

	private static final Logger log = LoggerFactory.getLogger(MortgageServiceIMPL.class);

	/**
	 * The value of this field is read from property file and will decide the
	 * protocall(SOAP/REST) to be used in flow
	 */
	@Value("${protocall}")
	private String protocall;

	/**
	 * This instance is protocall delegator implementation of rest/soap services
	 */
	private ProtocallDelegator protocallDelegator;

	/**
	 * During server start up , below will autowiring will decide which protocalll
	 * gateway needs to be invoked
	 * 
	 * @param context
	 */
	@Autowired
	public void setProtocallDelegator(ApplicationContext context) {
		log.info(protocall + "is used for Routing requests");
		protocallDelegator = (ProtocallDelegator) context.getBean(protocall);
	}

	/**
	 * This method Invokes getAllMortgages method on either Rest or SOAP Gateway
	 * 
	 * @param sortOrder
	 * @return GetAllMortgagesConsumerResponse
	 * @throws MortgageBusinessException
	 */
	public GetAllMortgagesConsumerResponse getAllMortgages(final String sortOrder) throws MortgageBusinessException {
		return protocallDelegator.getAllMortgages(sortOrder);
	}

	/**
	 * This method Invokes getAllMortgages method on either Rest or SOAP Gateway
	 * 
	 * @param MortgageDto
	 * @return CreateMortgageResponse
	 * @throws MortgageBusinessException
	 * @throws RecordNotFoundException
	 */
	@CreateMortgage
	public CreateMortgageResponse createMortgage(final MortgageDto reqEntity) throws MortgageBusinessException {
		CreateMortgageResponse response = new CreateMortgageResponse();
		response.setStatus(SUCCESS);
		return response;
	}

	/**
	 * This method Invokes getMaxVersion method on either Rest or SOAP Gateway
	 * 
	 * @param MortgageDto
	 * @return CreateMortgageResponse
	 * @throws MortgageBusinessException
	 * @throws RecordNotFoundException
	 */

	public Integer getMaxVersion(final String mortgageID) throws MortgageBusinessException {
		return protocallDelegator.getMaxVersionByMortgageID(mortgageID);
	}

	/**
	 * @return the protocall
	 */
	public String getProtocall() {
		return protocall;
	}

	/**
	 * @param protocall the protocall to set
	 */
	public void setProtocall(final String protocall) {
		this.protocall = protocall;
	}

	/**
	 * @return the protocallDelegator
	 */
	public ProtocallDelegator getProtocallDelegator() {
		return protocallDelegator;
	}

	/**
	 * @param protocallDelegator the protocallDelegator to set
	 */
	public void setProtocallDelegator(ProtocallDelegator protocallDelegator) {
		this.protocallDelegator = protocallDelegator;
	}

}
