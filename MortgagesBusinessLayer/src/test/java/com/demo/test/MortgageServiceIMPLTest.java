package com.demo.test;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.demo.CustomTest;
import com.generated.CreateMortgageResponse;
import com.mortgage.businesslayer.demo.dto.GetAllMortgagesConsumerResponse;
import com.mortgage.businesslayer.demo.dto.MortgageDto;
import com.mortgage.businesslayer.demo.dto.Mortgages;
import com.mortgage.businesslayer.demo.exception.MortgageBusinessException;
import com.mortgage.businesslayer.demo.service.impl.MortgageServiceIMPL;
import com.mortgage.businesslayer.demo.soapservice.handler.BackendSOAPServiceCallDelegator;

public class MortgageServiceIMPLTest extends CustomTest {

	@Mock
	private BackendSOAPServiceCallDelegator backendSOAPServiceCallDelegator;

	// @InjectMocks
	// private MortgageBusinessLayerController mortgageBusinessLayerController;
	@InjectMocks
	private MortgageServiceIMPL mortgageServiceIMPL;

	@Before
	public void setup() {
		mortgageServiceIMPL.setProtocallDelegator(backendSOAPServiceCallDelegator);
	}

	public static DateFormat Date_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

	@Test
	public void getAllMortgagesTest() throws ParseException, MortgageBusinessException {

		GetAllMortgagesConsumerResponse getAllMortgagesResponseOutputDto = new GetAllMortgagesConsumerResponse();
		List<Mortgages> mortgageDtoList = new ArrayList<Mortgages>();
		Mortgages mortgageDto = new Mortgages();
		mortgageDto.setMortgageIDReq("M1");
		mortgageDto.setVersionReq(1);
		mortgageDto.setCreatedDateReq(Date_FORMAT.parse("20/04/2015"));
		mortgageDto.setOfferDateReq(Date_FORMAT.parse("20/07/2017"));
		mortgageDtoList.add(mortgageDto);
		getAllMortgagesResponseOutputDto.setMortgages(mortgageDtoList);

		Mockito.when(backendSOAPServiceCallDelegator.getAllMortgages(any(String.class)))
				.thenReturn(getAllMortgagesResponseOutputDto);

		GetAllMortgagesConsumerResponse getAllMortgagesResponseDto = mortgageServiceIMPL.getAllMortgages("createdDate");

		assertNotNull(getAllMortgagesResponseDto);
		Mortgages mortgageDtoResponse = getAllMortgagesResponseDto.getMortgages().get(0);
		assertEquals(mortgageDtoResponse.getMortgageIDReq(), "M1");
		assertEquals(mortgageDtoResponse.getVersionReq(), new Integer(1));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		assertEquals(sdf.format(mortgageDto.getCreatedDateReq()), "20/04/2015");
		assertEquals(sdf.format(mortgageDto.getOfferDateReq()), "20/07/2017");
	}

	@Test
	public void getMaxversionTest() throws ParseException, MortgageBusinessException {
		Mockito.when(backendSOAPServiceCallDelegator.getMaxVersionByMortgageID(any(String.class))).thenReturn(3);

		Integer max = mortgageServiceIMPL.getMaxVersion("M1");
		assertNotNull(max);
		assertEquals(max, new Integer(3));
	}

	@Test
	public void createMortgagesTest() throws ParseException, MortgageBusinessException {
		//CreateMortgageResponse statusBackend=new CreateMortgageResponse();
		//Mockito.when(backendSOAPServiceCallDelegator.createMorgage(any(MortgageDto.class))).thenReturn("Success");
		MortgageDto mortgageDto = new MortgageDto();
		mortgageDto.setMortgageIDReq("M9");
		mortgageDto.setOfferDateReq(Date_FORMAT.parse("14/03/2021"));
		mortgageDto.setProductIDReq("B1");
		mortgageDto.setOfferIDReq("OI-1");
		mortgageDto.setVersionReq(1);
		CreateMortgageResponse status = mortgageServiceIMPL.createMortgage(mortgageDto);
		assertNotNull(status);
		assertEquals(status.getStatus(), "Success");
	}

}
