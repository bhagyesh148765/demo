package com.mortgage.businesslayer.demo.soapservice.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.client.RestTemplate;

import com.mortgage.businesslayer.demo.constant.MortgageConstant;
import com.mortgage.businesslayer.demo.soapservice.soapclient.MortgageSoapServiceClient;

/**
 * @author somnath.musib
 */
@Configuration
public class SoapClientConfig {

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder){
	    return builder.build();
	}
	
	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
		jaxb2Marshaller.setContextPath("com.generated");
		return jaxb2Marshaller;
	}

	@Bean
	public MortgageSoapServiceClient mortgageSoapServiceClient(Jaxb2Marshaller jaxb2Marshaller) {
		MortgageSoapServiceClient articleClient = new MortgageSoapServiceClient();
		articleClient.setDefaultUri(MortgageConstant.DATA_LAYER_SOAP_URL);
		articleClient.setMarshaller(jaxb2Marshaller);
		articleClient.setUnmarshaller(jaxb2Marshaller);
		return articleClient;
	}
}
