package com.demo;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mortgage.businesslayer.demo.BusinessLayerDemoApplicationMain;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = BusinessLayerDemoApplicationMain.class)
@WebAppConfiguration
@AutoConfigureMockMvc // need this in Spring Boot test
public abstract class CustomTest {
	protected MockMvc mvc;
	@Autowired
	WebApplicationContext webApplicationContext;
	public static DateFormat Date_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
	protected void setUp() {
		//mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}
}
