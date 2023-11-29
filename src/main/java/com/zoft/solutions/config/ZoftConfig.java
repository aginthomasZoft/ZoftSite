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
    Docket postsApiBlog() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("Zoftsolutions blog details").apiInfo(apiInfo()).select()
				.paths(regex("/blogs.*")).build();
	}

    @Bean
    Docket postsApiCaseStudy() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("Zoftsolutions case study details").apiInfo(apiInfo()).select()
				.paths(regex("/caseStudies.*")).build();
	}


    @Bean
    Docket postsApiContactUs() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("Zoftsolutions contact us details").apiInfo(apiInfo()).select()
				.paths(regex("/contactUs.*")).build();
	}

    @Bean
    Docket postsApiNewsLetter() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("Zoftsolutions news Letter details").apiInfo(apiInfo()).select()
				.paths(regex("/newsLetter.*")).build();
	}


    @Bean
    Docket postsApiLogin() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("Admin login details").apiInfo(apiInfo()).select()
				.paths(regex("/login.*")).build();
	}

    @Bean
    Docket postsApiCareers() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("Zoftsolutions careers details").apiInfo(apiInfo()).select()
				.paths(regex("/careers.*")).build();
	}

    @Bean
    Docket postsApiBanner() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("Zoftsolutions home banner details").apiInfo(apiInfo()).select()
				.paths(regex("/banners.*")).build();
	}

    @Bean
    Docket postsApiCompanyLogo() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("Zoftsolutions home company logo details").apiInfo(apiInfo()).select()
				.paths(regex("/companyLogos.*")).build();
	}

    @Bean
    Docket postsApiAboutUs() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("Zoftsolutions home aboutUs details").apiInfo(apiInfo()).select()
				.paths(regex("/aboutUs.*")).build();
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Zoftsolutions")
				.description("Sample Documentation Generateed Using SWAGGER2 for our Book Rest API")
				.termsOfServiceUrl("https://www.youtube.com/channel/UCORuRdpN2QTCKnsuEaeK-kQ")
				.license("Java_Gyan_Mantra License")
				.licenseUrl("https://www.youtube.com/channel/UCORuRdpN2QTCKnsuEaeK-kQ").version("1.0").build();
	}
}
