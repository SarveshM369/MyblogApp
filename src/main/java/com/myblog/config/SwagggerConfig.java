package com.myblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;



@Configuration
public class SwagggerConfig {
	
	String schemeName ="bearerScheme";

	 @Bean
	 public OpenAPI openAPI() {
		return new OpenAPI()
			.addSecurityItem(new SecurityRequirement()
					.addList(schemeName))	
			.components(new Components()
					.addSecuritySchemes(schemeName, new SecurityScheme()
							.name(schemeName)
							.type(SecurityScheme.Type.HTTP)
							.bearerFormat("JWT")
							 .scheme("bearer")))	
			.info(new Info()
					.title("Blog Project APIs")
					.description("This is blog project api developed by sarvesh")
					.version("1.0")
					.contact(new Contact().name("Sarvesh").email("sarveshm344@gmail.com").url("sarvesh.com"))
			        .license(new License().name("Apache"))
			        ).externalDocs(new ExternalDocumentation().url("learn with sarvesh").description("This is external url"));
			     
			       
	 }	
	 
	 
	 //This is my another method
	 
	 
//	 @Bean
//	    public OpenAPI openAPI() {
//	        return new OpenAPI()
//	            .info(new Info()
//	                    .title("Blog Project APIs")
//	                    .description("This is blog project api developed by sarvesh")
//	                    .version("1.0")
//	                    .contact(new Contact().name("Sarvesh").email("sarveshm344@gmail.com").url("sarvesh.com"))
//	                    .license(new License().name("Apache"))
//	            )
//	            .externalDocs(new ExternalDocumentation().url("learn with sarvesh").description("This is an external URL"))
//	            .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
//	            .components(new io.swagger.v3.oas.models.Components()
//	                    .addSecuritySchemes("bearerAuth", new SecurityScheme()
//	                            .type(SecurityScheme.Type.HTTP)
//	                            .scheme("bearer")
//	                            .bearerFormat("JWT")
//	                    )
//	            );
//	    }
}
