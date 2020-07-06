package com.mortgage.businesslayer.demo.soapservice.mapper;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.generated.CreateMortgageRequest;
import com.generated.MortgageDtoType;
import com.mortgage.businesslayer.demo.dto.MortgageDto;

public class MortgageSOAPRequestMapper {

	public CreateMortgageRequest mapSOAPRequest(final MortgageDto dto) {
		final CreateMortgageRequest createMortgageRequest = new CreateMortgageRequest();
		final MortgageDtoType mortgageDtoType = new MortgageDtoType();
		mortgageDtoType.setMortgageID(dto.getMortgageIDReq());
		mortgageDtoType.setProductID(dto.getProductIDReq());
		mortgageDtoType.setVersion(dto.getVersionReq());
		mortgageDtoType.setOfferID(dto.getOfferIDReq());
		mortgageDtoType.setProductID(dto.getProductIDReq());
		mortgageDtoType.setOfferDate(getXMLGregorianCalendarByDate(dto.getOfferDateReq()));
		// mortgageDtoType.setIsOfferExpired(dto.getIsOfferExpiredReq());
		// mortgageDtoType.setCreatedDate(getXMLGregorianCalendarByDate(dto.getCreatedDateReq()));
		createMortgageRequest.setMortgageDto(mortgageDtoType);
		return createMortgageRequest;
	}

	public XMLGregorianCalendar getXMLGregorianCalendarByDate(final Date date) {
		try {
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(date);
			return DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
