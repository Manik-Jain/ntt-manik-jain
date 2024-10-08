package com.mj.nttdata.assignment.manikjain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration

public class SwaggerConfig {

	@Bean
	public OpenAPI springShopOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("Manik Jain NTT Data API").description("Code assessment API for NTT Data")
						.version("v0.0.1").license(new License().name("Apache 2.0").url("http://springdoc.org")))
				.externalDocs(new ExternalDocumentation().description("Manik Jain NTT Data API")
						.url("https://github.com/Manik-Jain/ntt-manik-jain"));
	}
}
