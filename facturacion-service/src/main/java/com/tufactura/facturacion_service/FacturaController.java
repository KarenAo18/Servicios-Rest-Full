package com.tufactura.facturacion_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/facturas")
@CrossOrigin(origins = "*") // Para que puedas probarlo desde cualquier cliente
public class FacturaController {

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public List<Factura> listarFacturas() {
        return facturaRepository.findAll();
    }

    @GetMapping("/con-productos")
    public List<Factura> listarFacturasConProductos() {
        return facturaRepository.findAll();
    }

    @PostMapping
    public Factura crearFactura(@RequestBody Factura factura) {
        return facturaRepository.save(factura);
    }

    @GetMapping("/{id}")
    public Factura obtenerFactura(@PathVariable Long id) {
        return facturaRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Factura actualizarFactura(@PathVariable Long id, @RequestBody Factura facturaActualizada) {
        return facturaRepository.findById(id).map(factura -> {
            factura.setDescripcion(facturaActualizada.getDescripcion());
            factura.setTotal(facturaActualizada.getTotal());
            factura.setFecha(facturaActualizada.getFecha());
            return facturaRepository.save(factura);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void eliminarFactura(@PathVariable Long id) {
        facturaRepository.deleteById(id);
    }

    @PostMapping("/{facturaId}/productos/{productoId}")
    public ResponseEntity<String> asignarProductoAFactura(@PathVariable Long facturaId, @PathVariable Long productoId) {
        Optional<Factura> facturaOptional = facturaRepository.findById(facturaId);
        Optional<Producto> productoOptional = productoRepository.findById(productoId);

        if (facturaOptional.isPresent() && productoOptional.isPresent()) {
            Factura factura = facturaOptional.get();
            Producto producto = productoOptional.get();

            factura.getProductos().add(producto);
            facturaRepository.save(factura);

            return ResponseEntity.ok("Producto asignado correctamente a la factura.");
        } else {
            return ResponseEntity.badRequest().body("Factura o producto no encontrados.");
        }
    }
}
