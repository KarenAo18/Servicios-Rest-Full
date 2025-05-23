package com.tuproducto.productos;  // Asegúrate de cambiar esto a tu propio paquete

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")  // Esto aplica a cualquier ruta que comience con /api
                .allowedOrigins("http://127.0.0.1:5500")  // Cambia a la URL de tu frontend (si es otro puerto)
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Métodos permitidos
                .allowedHeaders("*")  // Permitir todos los encabezados
                .allowCredentials(true);  // Permitir enviar cookies o credenciales
    }
}
