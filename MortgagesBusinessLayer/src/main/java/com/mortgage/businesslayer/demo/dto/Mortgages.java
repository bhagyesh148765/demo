package com.mortgage.businesslayer.demo.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Mortgages implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6687966622513189672L;
	@NotNull
	private String mortgageIDReq;
	@NotNull
	private Integer versionReq;
	@NotNull
	private String offerIDReq;
	@NotNull
	private String productIDReq;
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "IST")
	private Date offerDateReq;
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "IST")
	private Date createdDateReq;
	private String isOfferExpired;

	/**
	 * @return the mortgageIDReq
	 */
	public String getMortgageIDReq() {
		return mortgageIDReq;
	}

	/**
	 * @param mortgageIDReq the mortgageIDReq to set
	 */
	public void setMortgageIDReq(String mortgageIDReq) {
		this.mortgageIDReq = mortgageIDReq;
	}

	/**
	 * @return the versionReq
	 */
	public Integer getVersionReq() {
		return versionReq;
	}

	/**
	 * @param versionReq the versionReq to set
	 */
	public void setVersionReq(Integer versionReq) {
		this.versionReq = versionReq;
	}

	/**
	 * @return the lastNameReq
	 */

	/**
	 * @return the offerIDReq
	 */
	public String getOfferIDReq() {
		return offerIDReq;
	}

	/**
	 * @param offerIDReq the offerIDReq to set
	 */
	public void setOfferIDReq(String offerIDReq) {
		this.offerIDReq = offerIDReq;
	}

	/**
	 * @return the productIDReq
	 */
	public String getProductIDReq() {
		return productIDReq;
	}

	/**
	 * @param productIDReq the productIDReq to set
	 */
	public void setProductIDReq(String productIDReq) {
		this.productIDReq = productIDReq;
	}

	/**
	 * @return the offerDateReq
	 */
	public Date getOfferDateReq() {
		return offerDateReq;
	}

	/**
	 * @param offerDateReq the offerDateReq to set
	 */
	public void setOfferDateReq(Date offerDateReq) {
		this.offerDateReq = offerDateReq;
	}

	/**
	 * @return the createdDateReq
	 */
	public Date getCreatedDateReq() {
		return createdDateReq;
	}

	/**
	 * @param createdDateReq the createdDateReq to set
	 */
	public void setCreatedDateReq(Date createdDateReq) {
		this.createdDateReq = createdDateReq;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MortgageDto [mortgageIDReq=" + mortgageIDReq + ", versionReq=" + versionReq + ", offerIDReq="
				+ offerIDReq + ", productIDReq=" + productIDReq + ", offerDateReq=" + offerDateReq + ", createdDateReq="
				+ createdDateReq + "]";
	}

	/**
	 * @return the isOfferExpired
	 */
	public String getIsOfferExpired() {
		return isOfferExpired;
	}

	/**
	 * @param isOfferExpired the isOfferExpired to set
	 */
	public void setIsOfferExpired(String isOfferExpired) {
		this.isOfferExpired = isOfferExpired;
	}

	/**
	 * 
	 */
	public Mortgages() {
		super();
	}

}
