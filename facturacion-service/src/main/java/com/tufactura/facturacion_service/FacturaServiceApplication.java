package com.tufactura.facturacion_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@EnableRetry
@EnableAsync
@OpenAPIDefinition(
    info = @Info(
        title = "Facturación Service API",
        version = "1.0",
        description = "Microservicio para gestión de facturas"
    )
    
)
@SpringBootApplication
public class FacturaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FacturaServiceApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}