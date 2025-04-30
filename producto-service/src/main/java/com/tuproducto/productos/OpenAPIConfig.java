package com.tuproducto.productos;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
    info = @Info(
        title = "Producto Service API",
        version = "v1",
        description = "Documentación de la API para gestionar productos"
    )
)
@Configuration
public class OpenAPIConfig {
    // Configuración adicional si es necesario
}
