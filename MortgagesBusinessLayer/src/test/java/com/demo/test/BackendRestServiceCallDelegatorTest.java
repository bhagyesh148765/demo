package com.demo.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.demo.CustomTest;
import com.mortgage.businesslayer.demo.dto.GetAllMortgagesBkndRestResponse;
import com.mortgage.businesslayer.demo.dto.GetAllMortgagesConsumerResponse;
import com.mortgage.businesslayer.demo.dto.MortgageDto;
import com.mortgage.businesslayer.demo.dto.Mortgages;
import com.mortgage.businesslayer.demo.restservice.client.MortgageRestServiceClient;
import com.mortgage.businesslayer.demo.restservice.handler.BackendRestServiceCallDelegator;

public class BackendRestServiceCallDelegatorTest extends CustomTest {

	@InjectMocks
	private BackendRestServiceCallDelegator backendRestServiceCallDelegator;

	@Mock
	private MortgageRestServiceClient restServiceClient;

	@Test
	public void getAllMortgagesTest() throws ParseException {

		final GetAllMortgagesBkndRestResponse getMortgagesResponse = new GetAllMortgagesBkndRestResponse();
		final Mortgages respnseDTO = new Mortgages();
		respnseDTO.setMortgageIDReq("M1");
		respnseDTO.setProductIDReq("B1");
		respnseDTO.setVersionReq(5);
		respnseDTO.setOfferIDReq("OF-6");
		respnseDTO.setCreatedDateReq(Date_FORMAT.parse("14/03/2021"));
		respnseDTO.setOfferDateReq(Date_FORMAT.parse("14/03/2021"));
		respnseDTO.setIsOfferExpired("Y");
		final List<Mortgages> mortgageDtoList = new ArrayList<Mortgages>();
		mortgageDtoList.add(respnseDTO);
		getMortgagesResponse.setMortgages(mortgageDtoList);

		Mockito.when(restServiceClient.getAllMortgagesRestCall(any(String.class))).thenReturn(getMortgagesResponse);

		GetAllMortgagesConsumerResponse getAllMortgagesResponseDto = backendRestServiceCallDelegator
				.getAllMortgages("createdDate");

		assertNotNull(getAllMortgagesResponseDto);
		Mortgages mortgageDtoResponse = getAllMortgagesResponseDto.getMortgages().get(0);
		assertEquals(mortgageDtoResponse.getMortgageIDReq(), "M1");
		assertEquals(mortgageDtoResponse.getVersionReq(), new Integer(5));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		assertEquals(sdf.format(mortgageDtoResponse.getCreatedDateReq()), "14/03/2021");
		assertEquals(sdf.format(mortgageDtoResponse.getOfferDateReq()), "14/03/2021");
	}

	public XMLGregorianCalendar getXMLGregorianCalendarByDate(final Date date) {
		try {
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(date);
			return DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Test
	public void getMaxVersionByMortgageIDTest() throws ParseException {
		Mockito.when(restServiceClient.getmaxVersionByMorgageIDRestCall(any(String.class))).thenReturn(5);
		Integer max = backendRestServiceCallDelegator.getMaxVersionByMortgageID("M1");
		assertNotNull(max);
		assertEquals(max, new Integer(5));
	}

	@Test
	public void createMortgagesTest() throws ParseException {
		Mockito.when(restServiceClient.createMortgageRestCall(any(MortgageDto.class))).thenReturn("Success");
		MortgageDto mortgageDto = new MortgageDto();
		mortgageDto.setMortgageIDReq("M9");
		mortgageDto.setOfferDateReq(Date_FORMAT.parse("14/03/2021"));
		mortgageDto.setProductIDReq("B1");
		mortgageDto.setOfferIDReq("OI-1");
		mortgageDto.setVersionReq(1);
		String status = backendRestServiceCallDelegator.createMorgage(mortgageDto);
		assertNotNull(status);
		assertEquals(status, "Success");
	}

}
