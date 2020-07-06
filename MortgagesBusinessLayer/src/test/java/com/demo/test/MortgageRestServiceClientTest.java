package com.demo.test;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.generated.GetMaxVersionByMortgageIDResponse;
import com.mortgage.businesslayer.demo.dto.GetAllMortgagesBkndRestResponse;
import com.mortgage.businesslayer.demo.dto.MortgageDto;
import com.mortgage.businesslayer.demo.dto.Mortgages;
import com.mortgage.businesslayer.demo.exception.MortgageBusinessException;
import com.mortgage.businesslayer.demo.restservice.client.MortgageRestServiceClient;

@RunWith(MockitoJUnitRunner.class)
public class MortgageRestServiceClientTest {

	public static DateFormat Date_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private MortgageRestServiceClient client;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void getMaxVersionByMortgageIDTest() throws MortgageBusinessException {
		GetMaxVersionByMortgageIDResponse maxVersionDataLayerReponseDto = new GetMaxVersionByMortgageIDResponse();
		maxVersionDataLayerReponseDto.setMaxVersion(5);
		Mockito.when(
				restTemplate.exchange(any(String.class), any(HttpMethod.class), any(HttpEntity.class), this.anyClass()))
				.thenReturn(new ResponseEntity(maxVersionDataLayerReponseDto, HttpStatus.OK));
		Integer max = client.getmaxVersionByMorgageIDRestCall("5");
		assertNotNull(max);
		assertEquals(max, new Integer(5));
	}


	@Test(expected = MortgageBusinessException.class)
	public void getMaxVersionByMortgageIDExceptionTest() throws MortgageBusinessException {
		GetMaxVersionByMortgageIDResponse maxVersionDataLayerReponseDto = new GetMaxVersionByMortgageIDResponse();
		maxVersionDataLayerReponseDto.setMaxVersion(5);
		Mockito.doThrow(new NullPointerException("backend communication")).when(restTemplate)
		.exchange(any(String.class), any(HttpMethod.class), any(HttpEntity.class),this.anyClass());
		Integer max = client.getmaxVersionByMorgageIDRestCall("5");
		assertNotNull(max);
		assertEquals(max, new Integer(5));
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void getAllMortgagesTest() throws ParseException, MortgageBusinessException {

		MortgageDto mortgageDto = new MortgageDto();
		mortgageDto.setMortgageIDReq("M9");
		mortgageDto.setOfferDateReq(Date_FORMAT.parse("14/03/2021"));
		mortgageDto.setProductIDReq("B1");
		mortgageDto.setOfferIDReq("OI-1");
		mortgageDto.setVersionReq(1);
		Mockito.when(restTemplate.postForEntity(any(String.class), any(HttpEntity.class), this.anyClass()))
				.thenReturn(new ResponseEntity(new String("Success"), HttpStatus.OK));
		String status = client.createMortgageRestCall(mortgageDto);
		assertNotNull(status);
		assertEquals(status, "Success");

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test(expected = MortgageBusinessException.class)
	public void getAllMortgagesExceptionTest() throws ParseException, MortgageBusinessException {

		MortgageDto mortgageDto = new MortgageDto();
		mortgageDto.setMortgageIDReq("M9");
		mortgageDto.setOfferDateReq(Date_FORMAT.parse("14/03/2021"));
		mortgageDto.setProductIDReq("B1");
		mortgageDto.setOfferIDReq("OI-1");
		mortgageDto.setVersionReq(1);
		Mockito.doThrow(new NullPointerException("backend communication")).when(restTemplate)
		.postForEntity(any(String.class), any(HttpEntity.class), this.anyClass());
		String status = client.createMortgageRestCall(mortgageDto);
		assertNotNull(status);
		assertEquals(status, "Success");

	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void createMortgagesTest() throws ParseException, MortgageBusinessException {

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
		Mockito.when(
				restTemplate.exchange(any(String.class), any(HttpMethod.class), any(HttpEntity.class), this.anyClass()))
				.thenReturn(new ResponseEntity(getMortgagesResponse, HttpStatus.OK));
		GetAllMortgagesBkndRestResponse response = client.getAllMortgagesRestCall("createdDate");
		assertNotNull(response);
		Mortgages mortgageDtoResponse = response.getMortgages().get(0);
		assertEquals(mortgageDtoResponse.getMortgageIDReq(), "M1");
		assertEquals(mortgageDtoResponse.getVersionReq(), new Integer(5));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		assertEquals(sdf.format(mortgageDtoResponse.getCreatedDateReq()), "14/03/2021");
		assertEquals(sdf.format(mortgageDtoResponse.getOfferDateReq()), "14/03/2021");
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test(expected = MortgageBusinessException.class)
	public void createMortgagesExceptionTest() throws ParseException, MortgageBusinessException {

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
		Mockito.doThrow(new NullPointerException("backend communication")).when(restTemplate)
		.exchange(any(String.class), any(HttpMethod.class), any(HttpEntity.class), this.anyClass());
		GetAllMortgagesBkndRestResponse response = client.getAllMortgagesRestCall("createdDate");
		assertNotNull(response);
		Mortgages mortgageDtoResponse = response.getMortgages().get(0);
		assertEquals(mortgageDtoResponse.getMortgageIDReq(), "M1");
		assertEquals(mortgageDtoResponse.getVersionReq(), new Integer(5));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		assertEquals(sdf.format(mortgageDtoResponse.getCreatedDateReq()), "14/03/2021");
		assertEquals(sdf.format(mortgageDtoResponse.getOfferDateReq()), "14/03/2021");
	}


	private class AnyClassMatcher implements ArgumentMatcher<Class<?>> {
		@Override
		public boolean matches(Class<?> argument) {
			return true;
		}
	}

	private Class<?> anyClass() {
		return Mockito.argThat(new AnyClassMatcher());
	}
}
