package com.demo.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.SpringConstraintValidatorFactory;
import org.springframework.web.context.WebApplicationContext;

import com.demo.CustomTest;
import com.generated.GetMortgagesResponse;
import com.generated.MortgageDtoType;
import com.mortgage.businesslayer.demo.controller.MortgageBusinessLayerController;
import com.mortgage.businesslayer.demo.dto.GetAllMortgagesConsumerResponse;
import com.mortgage.businesslayer.demo.dto.MortgageDto;
import com.mortgage.businesslayer.demo.dto.Mortgages;
import com.mortgage.businesslayer.demo.service.MortgageService;
import com.mortgage.businesslayer.demo.soapservice.soapclient.MortgageSoapServiceClient;
import com.mortgage.businesslayer.demo.validator.version.VersionValidator;

public class MortgageBusinessLayerControllerTest extends CustomTest {

	MortgageSoapServiceClient soapServiceClient;
	@Mock
	MortgageService service;

	@InjectMocks
	private MortgageBusinessLayerController mortgageBusinessLayerController;

	@Before
	public void setup() {
		mvc = MockMvcBuilders.standaloneSetup(mortgageBusinessLayerController).build();
	}

	public void populateGetMortgagesResponse() {
		final List<MortgageDtoType> mortgageDtoList = new ArrayList<MortgageDtoType>();
		final MortgageDtoType respnseDTO = new MortgageDtoType();
		respnseDTO.setMortgageID("M1");
		respnseDTO.setProductID("");
		respnseDTO.setVersion(5);
		respnseDTO.setOfferID("");
		respnseDTO.setProductID("");
		respnseDTO.setCreatedDate(null);
		respnseDTO.setOfferDate(null);
		respnseDTO.setIsOfferExpired("Y");
		mortgageDtoList.add(respnseDTO);
		final GetMortgagesResponse getMortgagesResponse = new GetMortgagesResponse();
		getMortgagesResponse.getMortgageDto().addAll(mortgageDtoList);
	}

	@Test
	public void getAllMortgagesSortByCreatedDateTest() throws Exception {
		GetAllMortgagesConsumerResponse getAllMortgagesResponseDto = new GetAllMortgagesConsumerResponse();
		List<Mortgages> mortgageDtoList = new ArrayList<Mortgages>();
		Mortgages mortgageDto = new Mortgages();
		mortgageDto.setMortgageIDReq("M1");
		mortgageDto.setVersionReq(1);
		mortgageDto.setCreatedDateReq(Date_FORMAT.parse("20/04/2015"));
		mortgageDto.setOfferDateReq(Date_FORMAT.parse("20/07/2017"));
		mortgageDtoList.add(mortgageDto);
		getAllMortgagesResponseDto.setMortgages(mortgageDtoList);
		Mockito.when(service.getAllMortgages(any(String.class))).thenReturn(getAllMortgagesResponseDto);
		String uri = "/MortgagesBusiness?orderBy=createdDate";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		System.out.println("im content: " + content);
		GetAllMortgagesConsumerResponse mortgagesResponseDTO = super.mapFromJson(content,
				GetAllMortgagesConsumerResponse.class);
		assertNotNull(mortgagesResponseDTO);
		Mortgages mortgageDtoResponse = mortgagesResponseDTO.getMortgages().get(0);
		assertEquals(mortgageDtoResponse.getMortgageIDReq(), "M1");
		assertEquals(mortgageDtoResponse.getVersionReq(), new Integer(1));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		assertEquals(sdf.format(mortgageDto.getCreatedDateReq()), "20/04/2015");
		assertEquals(sdf.format(mortgageDto.getOfferDateReq()), "20/07/2017");
	}

	@Test
	public void getAllMortgagesSortByOfferDateTest() throws Exception {
		GetAllMortgagesConsumerResponse getAllMortgagesResponseDto = new GetAllMortgagesConsumerResponse();
		List<Mortgages> mortgageDtoList = new ArrayList<Mortgages>();
		Mortgages mortgageDto = new Mortgages();
		mortgageDto.setMortgageIDReq("M1");
		mortgageDto.setVersionReq(1);
		mortgageDto.setCreatedDateReq(Date_FORMAT.parse("20/04/2015"));
		mortgageDto.setOfferDateReq(Date_FORMAT.parse("20/07/2017"));
		mortgageDtoList.add(mortgageDto);
		getAllMortgagesResponseDto.setMortgages(mortgageDtoList);
		Mockito.when(service.getAllMortgages(any(String.class))).thenReturn(getAllMortgagesResponseDto);
		String uri = "/MortgagesBusiness?orderBy=createdDate";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		GetAllMortgagesConsumerResponse mortgagesResponseDTO = super.mapFromJson(content,
				GetAllMortgagesConsumerResponse.class);
		assertNotNull(mortgagesResponseDTO);
		Mortgages mortgageDtoResponse = mortgagesResponseDTO.getMortgages().get(0);
		assertEquals(mortgageDtoResponse.getMortgageIDReq(), "M1");
		assertEquals(mortgageDtoResponse.getVersionReq(), new Integer(1));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		assertEquals(sdf.format(mortgageDto.getCreatedDateReq()), "20/04/2015");
		assertEquals(sdf.format(mortgageDto.getOfferDateReq()), "20/07/2017");
	}

	/*
	 * @Mock ConstraintValidatorContext context;
	 * 
	 * @Mock ConstraintViolationBuilder builder;
	 * 
	 * @Mock ConstraintValidatorContext constraintValidatorContext;
	 * 
	 * @Mock private VersionValidator versionValidator;
	 * 
	 * @Autowired private WebApplicationContext webApplicationContext;
	 * 
	 * LocalValidatorFactoryBean validator;
	 * 
	 * @Test public void createMortgageTest() throws Exception {
	 * 
	 * SpringConstraintValidatorFactory springConstraintValidatorFactory = new
	 * SpringConstraintValidatorFactory(
	 * webApplicationContext.getAutowireCapableBeanFactory()); validator = new
	 * LocalValidatorFactoryBean();
	 * validator.setConstraintValidatorFactory(springConstraintValidatorFactory);
	 * validator.setApplicationContext(webApplicationContext);
	 * validator.afterPropertiesSet();
	 * 
	 * context = Mockito.mock(ConstraintValidatorContext.class); builder =
	 * Mockito.mock(ConstraintValidatorContext.ConstraintViolationBuilder.class);
	 * Mockito.when(context.buildConstraintViolationWithTemplate(Mockito.anyString()
	 * )).thenReturn(builder);
	 * Mockito.when(service.getMaxVersion(any(String.class))).thenReturn(3);
	 * Mockito.when(versionValidator.isValid(any(MortgageDto.class),
	 * any(ConstraintValidatorContext.class))) .thenReturn(true); String uri =
	 * "/MortgagesBusiness/createMortgage"; MortgageDto mortgageDto = new
	 * MortgageDto(); mortgageDto.setMortgageIDReq("M9");
	 * mortgageDto.setOfferDateReq(Date_FORMAT.parse("14/03/2021"));
	 * mortgageDto.setProductIDReq("B1"); mortgageDto.setOfferIDReq("OI-1");
	 * mortgageDto.setVersionReq(1);
	 * Mockito.when(service.createMortgage(any(MortgageDto.class))).
	 * thenReturn("Mortgage creation is Successfull"); String inputJson =
	 * super.mapToJson(mortgageDto); MvcResult mvcResult = mvc.perform(
	 * MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE
	 * ).content(inputJson)) .andReturn(); int status =
	 * mvcResult.getResponse().getStatus(); assertEquals(200, status); String
	 * content = mvcResult.getResponse().getContentAsString();
	 * System.out.println(content); assertEquals(content,
	 * "Mortgage creation is Successfull"); }
	 */
}
