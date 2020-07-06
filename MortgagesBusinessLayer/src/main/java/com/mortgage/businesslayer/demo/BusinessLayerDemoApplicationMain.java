package com.mortgage.businesslayer.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BusinessLayerDemoApplicationMain {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(BusinessLayerDemoApplicationMain.class);
		application.setAdditionalProfiles("ssl");
		application.run(args);
	}
}
