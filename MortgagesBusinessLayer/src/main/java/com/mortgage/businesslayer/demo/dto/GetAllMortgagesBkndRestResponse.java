package com.mortgage.businesslayer.demo.dto;

import java.util.List;

public class GetAllMortgagesBkndRestResponse {

	private List<Mortgages> mortgages;

	/**
	 * @return the mortgages
	 */
	public List<Mortgages> getMortgages() {
		return mortgages;
	}

	/**
	 * @param mortgages the mortgages to set
	 */
	public void setMortgages(List<Mortgages> mortgages) {
		this.mortgages = mortgages;
	}

	/**
	 * @param mortgageDto
	 */
	public GetAllMortgagesBkndRestResponse(List<Mortgages> mortgagesList) {
		super();
		this.mortgages = mortgagesList;
	}

	/**
	 * 
	 */
	public GetAllMortgagesBkndRestResponse() {
		super();
	}

}
