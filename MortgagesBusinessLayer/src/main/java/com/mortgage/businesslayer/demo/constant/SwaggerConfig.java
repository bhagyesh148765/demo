package com.mortgage.businesslayer.demo.constant;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("public-api").apiInfo(apiInfo()).select()
				.paths(postPaths()).build();
	}

	private Predicate<String> postPaths() {
		return or(regex("/MortgagesBusiness.*"), regex("/createMortgage/createMortgage.*"));
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("JavaInUse API").description("Mortgages BusinessLayer API reference")
				.termsOfServiceUrl("http://business.com").license("bhagyesh License").licenseUrl("bhagy@gmail.com")
				.version("1.0").build();
	}

}