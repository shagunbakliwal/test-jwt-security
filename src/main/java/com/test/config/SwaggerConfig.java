package com.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	/*
	 * @Bean public Docket productApi(ServletContext servletContext) { return new
	 * Docket(DocumentationType.SWAGGER_2).pathProvider(new
	 * RelativePathProvider(servletContext) {
	 * 
	 * @Override public String getApplicationBasePath() { return "/api"; }
	 * }).select().apis(RequestHandlerSelectors.any()).paths(springfox.documentation
	 * .builders.PathSelectors.any()) .build().apiInfo(metaData()); }
	 */

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(springfox.documentation.builders.PathSelectors.any()).build().apiInfo(metaData());
	}

	private ApiInfo metaData() {
		return new ApiInfoBuilder().title("APIs created for testing security").description("Api Documentation")
				.version("1.0").contact(new Contact("devteam", "developers.security.com", "security@xyz.com")).build();
	}

}
