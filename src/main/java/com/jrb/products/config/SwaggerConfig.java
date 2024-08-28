package com.jrb.products.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

	@Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Documentación API Productos")
                        .version("1.0.0")
                        .description("Documentación de API REST"));
    }
	
	@Bean
    public GroupedOpenApi apiGroup() {
        return GroupedOpenApi.builder()
                .group("Controller") 
                .packagesToScan("com.jrb.products.controller")
                .build();
    }
	
}