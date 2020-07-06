package com.mortgage.datalayer.demo.controller;

import static com.mortgage.datalayer.demo.constant.MortgageConstant.Date_FORMAT;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.SimpleDateFormat;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.generated.GetMaxVersionByMortgageIDResponse;
import com.mortgage.datalayer.demo.CustomTest;
import com.mortgage.datalayer.demo.dto.CreateMortgageRestRequest;
import com.mortgage.datalayer.demo.dto.GetMortgagesRestResponse;
import com.mortgage.datalayer.demo.dto.Mortgages;

public class MortgageDataLayerControllerTest extends CustomTest {

	@Test
	public void getAllMortgagesSortByCreatedDateTest() throws Exception {
		String uri = "/Mortgages?orderBy=createdDate";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		System.out.println("im content: " + content);
		GetMortgagesRestResponse mortgagesResponseDTO = super.mapFromJson(content, GetMortgagesRestResponse.class);
		assertNotNull(mortgagesResponseDTO);
		Mortgages mortgageDto = mortgagesResponseDTO.getMortgages().get(0);
		assertEquals(mortgageDto.getMortgageIDReq(), "M1");
		assertEquals(mortgageDto.getVersionReq(), new Integer(1));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		assertEquals(sdf.format(mortgageDto.getCreatedDateReq()), "20/04/2015");
		assertEquals(sdf.format(mortgageDto.getOfferDateReq()), "20/07/2017");
	}

	@Test
	public void getAllMortgagesSortByOfferDateTest() throws Exception {
		String uri = "/Mortgages?orderBy=offerDate";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		System.out.println("im content: " + content);
		assertNotNull(content);
		GetMortgagesRestResponse mortgagesResponseDTO = super.mapFromJson(content, GetMortgagesRestResponse.class);
		assertNotNull(mortgagesResponseDTO);
		Mortgages mortgageDto = mortgagesResponseDTO.getMortgages().get(0);
		assertEquals(mortgageDto.getMortgageIDReq(), "M2");
		assertEquals(mortgageDto.getVersionReq(), new Integer(2));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		assertEquals(sdf.format(mortgageDto.getCreatedDateReq()), "20/05/2015");
		assertEquals(sdf.format(mortgageDto.getOfferDateReq()), "20/06/2017");
	}

	@Test
	public void getAllMortgagesSortByAnyRandomTest() throws Exception {
		String uri = "/Mortgages?orderBy=abc";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		System.out.println("im content: " + content);
		assertNotNull(content);
		GetMortgagesRestResponse mortgagesResponseDTO = super.mapFromJson(content, GetMortgagesRestResponse.class);
		assertNotNull(mortgagesResponseDTO);
		Mortgages mortgageDto = mortgagesResponseDTO.getMortgages().get(0);
		assertNotNull(mortgageDto);
	}

	@Test
	public void getAllMaxVersionByMortgageIDTest() throws Exception {
		String uri = "/Mortgages/getMaxVersion?mortgageID=M3";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		System.out.println("im content: " + content);
		GetMaxVersionByMortgageIDResponse mortgagesList = super.mapFromJson(content,
				GetMaxVersionByMortgageIDResponse.class);
		assertNotNull(mortgagesList);
	}

	@Test
	public void createMortgageTest() throws Exception {
		String uri = "/Mortgages/createMortgage";
		CreateMortgageRestRequest createMortgageRestRequest = new CreateMortgageRestRequest();
		createMortgageRestRequest.setMortgageIDReq("M9");
		createMortgageRestRequest.setOfferDateReq(Date_FORMAT.parse("14/03/2021"));
		createMortgageRestRequest.setProductIDReq("B1");
		createMortgageRestRequest.setOfferIDReq("OI-1");
		createMortgageRestRequest.setVersionReq(1);
		String inputJson = super.mapToJson(createMortgageRestRequest);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Mortgage creation is Successfull");
	}
}
