package com.tufactura.facturacion_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facturas")
// Configuración CORS para permitir solicitudes desde otro dominio (ajusta según tu frontend)
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class FacturaController {

    // Inyección del servicio FacturaService
    @Autowired
    private FacturaService facturaService;

    // Crear factura simple
    @PostMapping
    public Factura crearFactura(@RequestBody Factura factura) {
        System.out.println(factura);  // Esto imprimirá la factura recibida en la consola de Spring Boot
        return facturaService.crearFactura(factura);
    }


    // Crear factura con producto del otro servicio (producto-service)
    @PostMapping("/con-producto/{productoId}")
    public Factura crearFacturaConProducto(@PathVariable Long productoId,
                                           @RequestParam String cliente) {
        return facturaService.crearFacturaConProducto(productoId, cliente);  // Llamamos al método crearFacturaConProducto
    }

    // Obtener todas las facturas
    @GetMapping
    public List<Factura> obtenerFacturas() {
        return facturaService.obtenerFacturas();  // Obtenemos todas las facturas a través del servicio
    }

    // Eliminar una factura por su ID
    @DeleteMapping("/{id}")
    public void eliminarFactura(@PathVariable Long id) {
        facturaService.eliminarFactura(id);  // Llamamos al método para eliminar factura por ID
    }
}
