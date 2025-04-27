package com.tuproducto.productos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.CascadeType;  // Importar CascadeType

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre = "";
    private String descripcion = "";
    private Double precio = 0.0;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)  // Aquí se agrega el CascadeType.PERSIST
    @JoinColumn(name = "cliente_id")  // Asegúrate de que este campo apunte correctamente a la columna cliente_id en la tabla Producto
    private Cliente cliente;

    public Producto() {}

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
