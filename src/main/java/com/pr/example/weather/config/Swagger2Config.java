package com.pr.example.weather.config;

import javax.servlet.ServletContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * 
 * @author Prakash Rathod
 * 
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

	@Bean
	public Docket authAPI(ServletContext servletContext) {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).groupName("WEATHER")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.pr.example.weather.controller")).paths(PathSelectors.any())
				.build();
	}
	
	private ApiInfo apiInfo() {
		Contact contact = new Contact("Prakash Rathod", "https://www.linkedin.com/in/prakash-rathod-0a9a6312b", "prakashrathod1203@gmail.com");
		return new ApiInfoBuilder().title("Open Weather Map API").contact(contact).version("1.0").build();
	}
}
