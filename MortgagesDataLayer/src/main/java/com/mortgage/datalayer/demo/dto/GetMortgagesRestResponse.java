package com.mortgage.datalayer.demo.dto;

import java.io.Serializable;
import java.util.List;

public class GetMortgagesRestResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1977545868074073441L;
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
	 * @param mortgageResponseDtoList
	 * 
	 */
	public GetMortgagesRestResponse(List<Mortgages> mortgageResponseDtoList) {
		mortgages = mortgageResponseDtoList;
	}

	/**
	 * 
	 */
	public GetMortgagesRestResponse() {
		super();
	}

}
