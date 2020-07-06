package com.mortgage.businesslayer.demo.constant;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public interface MortgageConstant {

	public static DateFormat Date_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
	public static final String OFFER_DATE = "offerDate";
	public static final String CREATED_DATE = "createdDate";
	public static final String DATA_LAYER_SOAP_URL = "https://localhost:8080/MortgageDataLayer/MortgageSOAPService/";

}
