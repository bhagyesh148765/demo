package com.mortgage.businesslayer.demo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.mortgage.businesslayer.demo.dto.GetAllMortgagesConsumerResponse;
import com.mortgage.businesslayer.demo.dto.MortgageDto;
import com.mortgage.businesslayer.demo.exception.MortgageBusinessException;
import com.mortgage.businesslayer.demo.service.MortgageService;
import com.mortgage.businesslayer.demo.service.ProtocallDelegator;

@Service
public class MortgageServiceIMPL implements MortgageService {
	private static final Logger log = LoggerFactory.getLogger(MortgageServiceIMPL.class);

	@Value("${protocall}")
	private String protocall;

	private ProtocallDelegator protocallDelegator;

	@Autowired
	public void setProtocallDelegator(ApplicationContext context) {
		System.out.println("protocall   " + protocall);
		protocallDelegator = (ProtocallDelegator) context.getBean(protocall);
	}

	/**
	 * 
	 * @param sortOrder
	 * @return
	 * @throws MortgageBusinessException 
	 */
	public GetAllMortgagesConsumerResponse getAllMortgages(final String sortOrder) throws MortgageBusinessException {
		return protocallDelegator.getAllMortgages(sortOrder);
	}

	/**
	 * 
	 * @param reqEntity
	 * @return
	 * @throws MortgageBusinessException 
	 * @throws RecordNotFoundException
	 */
	public String createMortgage(MortgageDto reqEntity) throws MortgageBusinessException {
		return protocallDelegator.createMorgage(reqEntity);
	}

	public Integer getMaxVersion(String mortgageID) throws MortgageBusinessException {
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
	public void setProtocall(String protocall) {
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
