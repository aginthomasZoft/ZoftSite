package com.zoft.solutions.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class ZoftConfig {

	@Bean
	public Docket postsApi1() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("Zoftsolutions Blog").apiInfo(apiInfo()).select()
				.paths(regex("/Blog.*")).build();
	}

	@Bean
	public Docket postsApi2() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("Zoftsolutions Case Study").apiInfo(apiInfo()).select()
				.paths(regex("/Case.*")).build();
	}
	
	@Bean
	public Docket postsApi3() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("Zoftsolutions User Details").apiInfo(apiInfo()).select()
				.paths(regex("/User.*")).build();
	}
	
	@Bean
	public Docket postsApi4() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("Zoftsolutions ContactUs details").apiInfo(apiInfo()).select()
				.paths(regex("/ContactUs.*")).build();
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Zoftsolutions")
				.description("Sample Documentation Generateed Using SWAGGER2 for our Book Rest API")
				.termsOfServiceUrl("https://www.youtube.com/channel/UCORuRdpN2QTCKnsuEaeK-kQ")
				.license("Java_Gyan_Mantra License")
				.licenseUrl("https://www.youtube.com/channel/UCORuRdpN2QTCKnsuEaeK-kQ").version("1.0").build();
	}
}
