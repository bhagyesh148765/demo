package com.mortgage.datalayer.demo.soapservice.mapper;

import java.util.Date;

import javax.xml.datatype.XMLGregorianCalendar;

import com.generated.CreateMortgageRequest;
import com.generated.MortgageDtoType;
import com.mortgage.datalayer.demo.dto.MortgageDto;

public class MortgageSOAPRequestMapper {

	/***
	 * Converts Incoming SOAP Request CreateMortgageRequest to MortgageDTO
	 * 
	 * @param CreateMortgageRequest createMortgageRequest
	 * @return MortgageDto
	 */
	public MortgageDto mapSOAPRequestToDTO(final CreateMortgageRequest createMortgageRequest) {

		final MortgageDto respnseDTO = new MortgageDto();
		MortgageDtoType mortgageDtoType = createMortgageRequest.getMortgageDto();
		respnseDTO.setMortgageID(mortgageDtoType.getMortgageID());
		respnseDTO.setProductID(mortgageDtoType.getProductID());
		respnseDTO.setVersion(mortgageDtoType.getVersion());
		respnseDTO.setOfferID(mortgageDtoType.getOfferID());
		respnseDTO.setProductID(mortgageDtoType.getProductID());
		respnseDTO.setCreatedDate(xmlGregorianCalendartoDate(mortgageDtoType.getCreatedDate()));
		respnseDTO.setOfferDate(xmlGregorianCalendartoDate(mortgageDtoType.getOfferDate()));
		return respnseDTO;

	}

	/**
	 * Converts XMLGregorianCalendar to java.util.Date in Java
	 * 
	 * @param XMLGregorianCalendar calendar
	 * @return Date
	 */
	public static Date xmlGregorianCalendartoDate(XMLGregorianCalendar calendar) {
		if (calendar == null) {
			return null;
		}
		return calendar.toGregorianCalendar().getTime();
	}

}
