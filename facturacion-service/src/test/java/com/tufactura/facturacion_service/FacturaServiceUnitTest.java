package com.tufactura.facturacion_service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FacturaServiceUnitTest {

    @Mock
    private FacturaRepository facturaRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private FacturaService facturaService;

    public FacturaServiceUnitTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCrearFacturaConProducto() {
        // Crear el producto simulado
        ProductoDTO productoMock = new ProductoDTO();
        productoMock.setNombre("Producto Test");
        productoMock.setPrecio(100.0);

        // Simular la respuesta del RestTemplate
        when(restTemplate.getForObject("http://localhost:8081/productos/1", ProductoDTO.class))
                .thenReturn(productoMock);

        // Simular el guardado en el repositorio
        Factura facturaEsperada = new Factura();
        facturaEsperada.setCliente("Cliente Test");
        facturaEsperada.setDescripcion("Producto: Producto Test");
        facturaEsperada.setMonto(100.0);
        facturaEsperada.setTotal(100.0);
        facturaEsperada.setFecha(LocalDate.now());

        when(facturaRepository.save(any(Factura.class))).thenReturn(facturaEsperada);

        // Ejecutar
        Factura facturaResultado = facturaService.crearFacturaConProducto(1L, "Cliente Test");

        // Verificar
        assertNotNull(facturaResultado);
        assertEquals("Cliente Test", facturaResultado.getCliente());
        assertEquals("Producto: Producto Test", facturaResultado.getDescripcion());
        assertEquals(100.0, facturaResultado.getMonto());
    }
}
