package com.mortgage.businesslayer.demo.dto;

import java.util.ArrayList;
import java.util.List;

public class GetAllMortgagesConsumerResponse {

	private List<Mortgages> mortgages;

	/**
	 * @return the mortgageDto
	 */
	public List<Mortgages> getMortgages() {
		if (mortgages == null) {
			mortgages = new ArrayList<Mortgages>();
		}
		return this.mortgages;
	}

	/**
	 * @param mortgageDto the mortgageDto to set
	 */
	public void setMortgages(final List<Mortgages> mortgageDto) {
		this.mortgages = mortgageDto;
	}

	/**
	 * 
	 */
	public GetAllMortgagesConsumerResponse() {
		super();
	}
}
