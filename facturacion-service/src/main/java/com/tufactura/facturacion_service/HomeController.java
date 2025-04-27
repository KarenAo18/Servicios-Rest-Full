package com.tufactura.facturacion_service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/")
public class HomeController {

    /**
     * Endpoint raíz que muestra información del servicio
     */
    @Operation(
        summary = "Endpoint de bienvenida",
        description = "Proporciona información básica del servicio de facturación",
        responses = {
            @ApiResponse(responseCode = "200", description = "Mensaje de bienvenida")
        }
    )
    @GetMapping
    public ResponseEntity<String> home() {
        String welcomeMessage = """
            <!DOCTYPE html>
            <html>
            <head>
                <title>Servicio de Facturación</title>
                <style>
                    body { font-family: Arial, sans-serif; text-align: center; margin-top: 50px; }
                    h1 { color: #2c3e50; }
                    .links { margin-top: 20px; }
                    a { margin: 0 10px; color: #3498db; text-decoration: none; }
                </style>
            </head>
            <body>
                <h1>Bienvenido al Servicio de Facturación</h1>
                <p>Versión: 1.0.0</p>
                <div class="links">
                    <a href="/swagger-ui.html">Documentación API</a> |
                    <a href="/facturas">Listar Facturas</a> |
                    <a href="/actuator/health">Estado del Servicio</a>
                </div>
            </body>
            </html>
            """;
        
        return ResponseEntity.ok()
            .header("Content-Type", "text/html; charset=UTF-8")
            .body(welcomeMessage);
    }

    /**
     * Endpoint de health check simplificado
     */
    @Operation(hidden = true)
    @GetMapping("/status")
    public ResponseEntity<String> status() {
        return ResponseEntity.ok("Servicio en funcionamiento");
    }
}