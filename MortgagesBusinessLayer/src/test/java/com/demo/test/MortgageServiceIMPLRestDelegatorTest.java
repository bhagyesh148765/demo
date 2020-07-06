package com.demo.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.demo.CustomTest;
import com.mortgage.businesslayer.demo.BusinessLayerDemoApplicationMain;
import com.mortgage.businesslayer.demo.controller.MortgageBusinessLayerController;
import com.mortgage.businesslayer.demo.restservice.handler.BackendRestServiceCallDelegator;
import com.mortgage.businesslayer.demo.service.impl.MortgageServiceIMPL;

@TestPropertySource("classpath:applicationtestrest.properties")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BusinessLayerDemoApplicationMain.class)
public class MortgageServiceIMPLRestDelegatorTest extends CustomTest {


	@Autowired
	private MortgageServiceIMPL mortgageServiceIMPL;

	@InjectMocks
	private MortgageBusinessLayerController mortgageBusinessLayerController;

	@Before
	public void setup() {
		mvc = MockMvcBuilders.standaloneSetup(mortgageBusinessLayerController).build();
	}

	@Test
	public void soapDelgatorTest() throws ParseException {
		assertNotNull(mortgageServiceIMPL.getProtocallDelegator());
		assertNotNull(mortgageServiceIMPL.getProtocall());
		assertTrue(mortgageServiceIMPL.getProtocall().equals("REST_SERVICE"));
		assertTrue(mortgageServiceIMPL.getProtocallDelegator() instanceof BackendRestServiceCallDelegator);
	}

}
