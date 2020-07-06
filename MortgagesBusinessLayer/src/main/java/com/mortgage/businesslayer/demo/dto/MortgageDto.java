package com.mortgage.businesslayer.demo.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mortgage.businesslayer.demo.validator.offerdate.ValidateOfferDate;
import com.mortgage.businesslayer.demo.validator.version.ValidateVersion;

@ValidateVersion
public class MortgageDto {

	@NotNull(message = "Passport should have atleast 2 characters")
	private String mortgageIDReq;
	@NotNull
	private Integer versionReq;

	private String offerIDReq;

	private String productIDReq;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@ValidateOfferDate
	private Date offerDateReq;
	@JsonFormat(pattern = "dd/MM/yyyy")
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

	@Override
	public String toString() {
		return "MortgageDto [mortgageIDReq=" + mortgageIDReq + ", versionReq=" + versionReq + ", offerIDReq="
				+ offerIDReq + ", productIDReq=" + productIDReq + ", offerDateReq=" + offerDateReq + ", createdDateReq="
				+ createdDateReq + ", isOfferExpired=" + isOfferExpired + "]";
	}

	/**
	 * 
	 */
	public MortgageDto() {
		super();
	}

}
