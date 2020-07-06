package com.demo.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.demo.CustomTest;
import com.generated.CreateMortgageRequest;
import com.generated.CreateMortgageResponse;
import com.generated.GetMaxVersionByMortgageIDRequest;
import com.generated.GetMaxVersionByMortgageIDResponse;
import com.generated.GetMortgagesRequest;
import com.generated.GetMortgagesResponse;
import com.generated.MortgageDtoType;
import com.mortgage.businesslayer.demo.dto.GetAllMortgagesConsumerResponse;
import com.mortgage.businesslayer.demo.dto.MortgageDto;
import com.mortgage.businesslayer.demo.dto.Mortgages;
import com.mortgage.businesslayer.demo.soapservice.handler.BackendSOAPServiceCallDelegator;
import com.mortgage.businesslayer.demo.soapservice.soapclient.MortgageSoapServiceClient;

public class BackendSOAPServiceCallDelegatorTest extends CustomTest {

	@InjectMocks
	private BackendSOAPServiceCallDelegator backendSOAPServiceCallDelegator;

	@Mock
	private MortgageSoapServiceClient soapServiceClient;
	

	@Test
	public void getAllMortgagesTest() throws ParseException {

		final GetMortgagesResponse getMortgagesResponse = new GetMortgagesResponse();
		final MortgageDtoType respnseDTO = new MortgageDtoType();
		respnseDTO.setMortgageID("M1");
		respnseDTO.setProductID("B1");
		respnseDTO.setVersion(5);
		respnseDTO.setOfferID("OF-6");
		respnseDTO.setCreatedDate(getXMLGregorianCalendarByDate(Date_FORMAT.parse("14/03/2021")));
		respnseDTO.setOfferDate(getXMLGregorianCalendarByDate(Date_FORMAT.parse("14/03/2021")));
		respnseDTO.setIsOfferExpired("Y");
		getMortgagesResponse.getMortgageDto().add(respnseDTO);

		Mockito.when(soapServiceClient.getAllMortgagesSoapCall(any(GetMortgagesRequest.class)))
				.thenReturn(getMortgagesResponse);

		GetAllMortgagesConsumerResponse getAllMortgagesResponseDto = backendSOAPServiceCallDelegator
				.getAllMortgages("createdDate");
		assertNotNull(getAllMortgagesResponseDto);
		Mortgages mortgageDtoResponse = getAllMortgagesResponseDto.getMortgages().get(0);
		assertEquals(mortgageDtoResponse.getMortgageIDReq(), "M1");
		assertEquals(mortgageDtoResponse.getVersionReq(), new Integer(5));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		assertEquals(sdf.format(mortgageDtoResponse.getCreatedDateReq()), "14/03/2021");
		assertEquals(sdf.format(mortgageDtoResponse.getOfferDateReq()), "14/03/2021");
	}

	@Test
	public void getAllMortgagesWithNullDateTest() throws ParseException {

		final GetMortgagesResponse getMortgagesResponse = new GetMortgagesResponse();
		final MortgageDtoType respnseDTO = new MortgageDtoType();
		respnseDTO.setMortgageID("M1");
		respnseDTO.setProductID("B1");
		respnseDTO.setVersion(5);
		respnseDTO.setOfferID("OF-6");
		respnseDTO.setCreatedDate(null);
		respnseDTO.setOfferDate(null);
		respnseDTO.setIsOfferExpired("Y");
		getMortgagesResponse.getMortgageDto().add(respnseDTO);

		Mockito.when(soapServiceClient.getAllMortgagesSoapCall(any(GetMortgagesRequest.class)))
				.thenReturn(getMortgagesResponse);

		GetAllMortgagesConsumerResponse getAllMortgagesResponseDto = backendSOAPServiceCallDelegator
				.getAllMortgages("createdDate");

		assertNotNull(getAllMortgagesResponseDto);
		Mortgages mortgageDtoResponse = getAllMortgagesResponseDto.getMortgages().get(0);
		assertEquals(mortgageDtoResponse.getMortgageIDReq(), "M1");
		assertEquals(mortgageDtoResponse.getVersionReq(), new Integer(5));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		assertNull(mortgageDtoResponse.getCreatedDateReq());
		assertNull(mortgageDtoResponse.getOfferDateReq());
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

		GetMaxVersionByMortgageIDResponse response = new GetMaxVersionByMortgageIDResponse();
		response.setMaxVersion(5);
		Mockito.when(soapServiceClient.getmaxVersionByMorgageIDSoapCall(any(GetMaxVersionByMortgageIDRequest.class)))
				.thenReturn(response);
		Integer max = backendSOAPServiceCallDelegator.getMaxVersionByMortgageID("M1");
		assertNotNull(max);
		assertEquals(max, new Integer(5));
	}

	@Test
	public void createMortgagesTest() throws ParseException {
		CreateMortgageResponse response = new CreateMortgageResponse();
		response.setStatus("Sucess");
		Mockito.when(soapServiceClient.createMortgageSoapCall(any(CreateMortgageRequest.class))).thenReturn(response);
		MortgageDto mortgageDto = new MortgageDto();
		mortgageDto.setMortgageIDReq("M9");
		mortgageDto.setOfferDateReq(Date_FORMAT.parse("14/03/2021"));
		mortgageDto.setProductIDReq("B1");
		mortgageDto.setOfferIDReq("OI-1");
		mortgageDto.setVersionReq(1);
		String status = backendSOAPServiceCallDelegator.createMorgage(mortgageDto);
		assertNotNull(status);
		assertEquals(status, "Sucess");
	}

}
