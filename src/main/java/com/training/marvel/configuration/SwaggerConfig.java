package com.training.marvel.configuration;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@SuppressWarnings("deprecation")
@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebFluxConfigurationSupport {

	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("classpath:META-INF/resources/"))              
          .paths(PathSelectors.any())                          
          .build()
          .apiInfo(apiInfo()); 
	}

	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:META-INF/resources/");
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfo(
		          "Neosoft Groupe", 
		          "PROJECT USING SPRING BOOT & JAVA 8", 
		          null, null, new Contact("Ahmed Taha Belghith",
		        		  "a.belghith@neo-soft.fr", "ahmedtahabelghith@gmail.com"), null, null, Collections.emptyList());
	  }
}