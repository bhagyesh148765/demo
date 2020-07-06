package com.mortgage.datalayer.demo.soapservice.mapper;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.generated.GetMortgagesResponse;
import com.generated.MortgageDtoType;
import com.mortgage.datalayer.demo.dto.MortgageDto;

public class MortgageSOAPResponseMapper {

	public GetMortgagesResponse mapGetMortgagesResponse(final List<MortgageDto> mortgageList) {
		final List<MortgageDtoType> mortgageResponseDtoList = mortgageList.stream().map(dto -> {
			final MortgageDtoType respnseDTO = new MortgageDtoType();
			respnseDTO.setMortgageID(dto.getMortgageID());
			respnseDTO.setProductID(dto.getProductID());
			respnseDTO.setVersion(dto.getVersion());
			respnseDTO.setOfferID(dto.getOfferID());
			respnseDTO.setProductID(dto.getProductID());
			respnseDTO.setCreatedDate(getXMLGregorianCalendarByDate(dto.getCreatedDate()));
			respnseDTO.setOfferDate(getXMLGregorianCalendarByDate(dto.getOfferDate()));
			respnseDTO.setIsOfferExpired(dto.getIsOfferExpired());
			return respnseDTO;
		}).collect(Collectors.toList());
		final GetMortgagesResponse getMortgagesResponse = new GetMortgagesResponse();
		getMortgagesResponse.getMortgageDto().addAll(mortgageResponseDtoList);
		return getMortgagesResponse;
	}

	public XMLGregorianCalendar getXMLGregorianCalendarByDate(final Date date) {
		try {
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(date);
			return DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return null;
	}

}
