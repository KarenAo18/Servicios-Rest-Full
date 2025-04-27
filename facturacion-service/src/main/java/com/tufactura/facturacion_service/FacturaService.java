package com.tufactura.facturacion_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private RestTemplate restTemplate;

    // Guardar una factura simple
    public Factura crearFactura(Factura factura) {
        factura.setFecha(LocalDate.now());
        return facturaRepository.save(factura);
    }

    // Crear una factura con producto del otro servicio
    public Factura crearFacturaConProducto(Long productoId, String cliente) {
        String url = "http://localhost:8081/productos/" + productoId; // Ajusta el puerto si tu microservicio de productos usa uno distinto
        ProductoDTO producto = restTemplate.getForObject(url, ProductoDTO.class);

        if (producto != null) {
            Factura facturaBase = new Factura();
            facturaBase.setCliente(cliente);
            facturaBase.setTotal(producto.getPrecio());
            facturaBase.setDescripcion("Producto: " + producto.getNombre());
            facturaBase.setMonto(producto.getPrecio());
            facturaBase.setFecha(LocalDate.now());
            return facturaRepository.save(facturaBase);
        } else {
            throw new RuntimeException("Producto no encontrado");
        }
    }

    // Obtener todas las facturas
    public List<Factura> obtenerFacturas() {
        return facturaRepository.findAll();
    }

    // Obtener factura por ID
    public Factura obtenerFacturaPorId(Long id) {
        return facturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada con ID: " + id));
    }

    // Eliminar factura
    public void eliminarFactura(Long id) {
        facturaRepository.deleteById(id);
    }
}
