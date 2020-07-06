package com.mortgage.businesslayer.demo.soapservice.mapper;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.datatype.XMLGregorianCalendar;

import com.generated.GetMortgagesResponse;
import com.generated.MortgageDtoType;
import com.mortgage.businesslayer.demo.dto.GetAllMortgagesConsumerResponse;
import com.mortgage.businesslayer.demo.dto.Mortgages;

public class MortgageSOAPResponseMapper {

	public GetAllMortgagesConsumerResponse mapSOAPResponse(GetMortgagesResponse response) {
		Collection<MortgageDtoType> mortgageList = response.getMortgageDto();
		final List<Mortgages> mortgageDtoList = mortgageList.stream().map(dto -> {
			final Mortgages respnseDTO = new Mortgages();
			respnseDTO.setMortgageIDReq(dto.getMortgageID());
			respnseDTO.setProductIDReq(dto.getProductID());
			respnseDTO.setVersionReq(dto.getVersion());
			respnseDTO.setOfferIDReq(dto.getOfferID());
			respnseDTO.setProductIDReq(dto.getProductID());
			respnseDTO.setCreatedDateReq(xmlGregorianCalendartoDate(dto.getCreatedDate()));
			respnseDTO.setOfferDateReq(xmlGregorianCalendartoDate(dto.getOfferDate()));
			respnseDTO.setIsOfferExpired(dto.getIsOfferExpired());
			return respnseDTO;
		}).collect(Collectors.toList());
		final GetAllMortgagesConsumerResponse getMortgagesResponse = new GetAllMortgagesConsumerResponse();
		getMortgagesResponse.getMortgages().addAll(mortgageDtoList);
		return getMortgagesResponse;
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
