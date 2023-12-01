package com.example.practice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Backend Developer Practical Test",
                description = "Practical Test", version = "1.0.0",
                contact = @Contact(
                        name = "Odintcov Dmitrii",
                        email = "d.odintcov@astondevs.ru"
                )
        )
)
public class OpenApiConfig {

}
