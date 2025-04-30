package com.tufactura.facturacion_service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {
    // No se necesita ninguna consulta especial ahora
}
