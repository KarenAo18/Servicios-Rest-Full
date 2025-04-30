package com.tufactura.facturacion_service;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;
import com.tufactura.facturacion_service.Producto;  // Importar la clase Producto

@Entity
@Data
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;
    private Double total;
    private LocalDate fecha;

    @ManyToMany
    @JoinTable(
        name = "factura_producto",  // Nombre de la tabla intermedia
        joinColumns = @JoinColumn(name = "factura_id"),  // Columna de la factura
        inverseJoinColumns = @JoinColumn(name = "producto_id")  // Columna de producto
    )
    private List<Producto> productos;  // Relación con productos
}
