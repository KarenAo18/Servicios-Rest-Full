package com.tufactura.facturacion_service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class FacturacionServiceApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
        // Prueba básica de carga del contexto
    }

    @Test
    void testHomeEndpoint() throws Exception {
        mockMvc.perform(get("/"))
               .andExpect(status().isOk())
               .andExpect(content().contentType("text/html;charset=UTF-8"))
               .andExpect(content().string(containsString("Servicio de Facturación")));
    }

    @Test
    void testListarFacturas() throws Exception {
        mockMvc.perform(get("/api/facturas"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(0))));
    }

    @Test
    void testCrearFacturaConProducto() throws Exception {
        String requestJson = """
            {
                "cliente": "Test Cliente",
                "fecha": "2025-04-25"
            }
            """;

        mockMvc.perform(post("/api/facturas/con-producto/1")
               .param("cliente", "Test Cliente")
               .contentType("application/json")
               .content(requestJson))
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.cliente").value("Test Cliente"))
               .andExpect(jsonPath("$.nombreProducto").exists());
    }

    @Test
    void testProductoNoEncontrado() throws Exception {
        String requestJson = "{\"cliente\":\"Test\"}";

        mockMvc.perform(post("/api/facturas/con-producto/999")
               .param("cliente", "Test")
               .contentType("application/json")
               .content(requestJson))
               .andExpect(status().isNotFound());
    }
}