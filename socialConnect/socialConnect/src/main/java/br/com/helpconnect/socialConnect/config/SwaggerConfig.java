package br.com.helpconnect.socialConnect.config;

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

@EnableSwagger2
@Configuration
public class SwaggerConfig {
	
	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.apis( RequestHandlerSelectors.basePackage
			("br.com.helpconnect.socialConnect.controller") )
			.paths(PathSelectors.any())
			.build()
			.apiInfo(apiInfo());	
		
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
			.title("API - Social Connect")
			.description("API de rede social")
			.version("2.0")
			.contact(contact())
			.build();
		
	}
	
	private Contact contact() {
		return new Contact("Kevin Alec Neri Lazzarotto",
			"https://github.com/Clamant96",
			"ADS | Desenvolvedor Web Java Junior Full Stack");
		
	}


}
