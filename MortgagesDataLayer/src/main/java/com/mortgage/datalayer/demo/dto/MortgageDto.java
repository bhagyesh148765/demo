package com.mortgage.datalayer.demo.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MortgageDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6687966622513189672L;
	@NotNull
	private String mortgageID;
	@NotNull
	private Integer version;
	@NotNull
	private String offerID;
	@NotNull
	private String productID;
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "IST")
	private Date offerDate;
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "IST")
	private Date createdDate;
	private String isOfferExpired;

	/**
	 * @return the mortgageID
	 */
	public String getMortgageID() {
		return mortgageID;
	}

	/**
	 * @param mortgageID the mortgageID to set
	 */
	public void setMortgageID(String mortgageID) {
		this.mortgageID = mortgageID;
	}

	/**
	 * @return the version
	 */
	public Integer getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * @return the offerID
	 */
	public String getOfferID() {
		return offerID;
	}

	/**
	 * @param offerID the offerID to set
	 */
	public void setOfferID(String offerID) {
		this.offerID = offerID;
	}

	/**
	 * @return the productID
	 */
	public String getProductID() {
		return productID;
	}

	/**
	 * @param productID the productID to set
	 */
	public void setProductID(String productID) {
		this.productID = productID;
	}

	/**
	 * @return the offerDate
	 */
	public Date getOfferDate() {
		return offerDate;
	}

	/**
	 * @param offerDate the offerDate to set
	 */
	public void setOfferDate(Date offerDate) {
		this.offerDate = offerDate;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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

}
