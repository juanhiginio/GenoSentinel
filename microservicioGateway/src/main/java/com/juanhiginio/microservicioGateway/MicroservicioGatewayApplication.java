package com.juanhiginio.microservicioGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
        title = "API Gateway Documentación",
        version = "1.0",
        description = "Documentación para el Microservicio Gateway"
))
public class MicroservicioGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroservicioGatewayApplication.class, args);
    }

}
