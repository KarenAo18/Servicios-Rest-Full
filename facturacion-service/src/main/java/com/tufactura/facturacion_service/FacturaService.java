package com.tufactura.facturacion_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    public List<Factura> listarFacturas() {
        return facturaRepository.findAll();
    }

    public Factura guardarFactura(Factura factura) {
        return facturaRepository.save(factura);
    }

    public Factura obtenerFacturaPorId(Long id) {
        return facturaRepository.findById(id).orElse(null);
    }

    public void eliminarFactura(Long id) {
        facturaRepository.deleteById(id);
    }
}
