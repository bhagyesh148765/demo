package com.mortgage.datalayer.demo.model;

import java.util.Date;

public class MortgageEntity {

	private String mortgageID;

	private Integer version;

	private String offerID;

	private String productID;

	private Date offerDate;

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

	/**
	 * @param mortgageID
	 * @param version
	 * @param offerID
	 * @param productID
	 * @param offerDate
	 * @param createdDate
	 * @param isOfferExpired
	 */
	public MortgageEntity(final String mortgageID, final Integer version, final String offerID, final String productID,
			final Date offerDate, final Date createdDate, final String isOfferExpired) {
		super();
		this.mortgageID = mortgageID;
		this.version = version;
		this.offerID = offerID;
		this.productID = productID;
		this.offerDate = offerDate;
		this.createdDate = createdDate;
		this.isOfferExpired = isOfferExpired;
	}

	public MortgageEntity() {

	}


}