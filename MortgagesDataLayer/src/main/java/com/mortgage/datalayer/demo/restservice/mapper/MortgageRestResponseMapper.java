package com.mortgage.datalayer.demo.restservice.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.mortgage.datalayer.demo.dto.GetMortgagesRestResponse;
import com.mortgage.datalayer.demo.dto.MortgageDto;
import com.mortgage.datalayer.demo.dto.Mortgages;

/**
 * Class responsible for Rest reponse creation from database entities
 * 
 * @author bhagyesh
 *
 */
public class MortgageRestResponseMapper {

	/**
	 * this method converts mortgage database enitites into rest response
	 * 
	 * @param mortgageList
	 * @return
	 */
	public GetMortgagesRestResponse mapGetMortgagesResponse(final List<MortgageDto> mortgageList) {
		final List<Mortgages> mortgageResponseDtoList = mortgageList.stream().map(dto -> {
			Mortgages mortgagesResponseObj = new Mortgages();
			mortgagesResponseObj.setMortgageIDReq(dto.getMortgageID());
			mortgagesResponseObj.setProductIDReq(dto.getProductID());
			mortgagesResponseObj.setVersionReq(dto.getVersion());
			mortgagesResponseObj.setOfferIDReq(dto.getOfferID());
			mortgagesResponseObj.setProductIDReq(dto.getProductID());
			mortgagesResponseObj.setCreatedDateReq(dto.getCreatedDate());
			mortgagesResponseObj.setOfferDateReq(dto.getOfferDate());
			mortgagesResponseObj.setIsOfferExpired(dto.getIsOfferExpired());
			return mortgagesResponseObj;
		}).collect(Collectors.toList());
		return new GetMortgagesRestResponse(mortgageResponseDtoList);

	}
}
