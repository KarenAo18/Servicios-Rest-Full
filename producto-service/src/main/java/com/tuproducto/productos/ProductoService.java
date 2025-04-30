package com.tuproducto.productos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    // Obtener todos los productos
    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    // Obtener un producto por su ID
    public Producto obtenerPorId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado"));
    }

    // Crear un nuevo producto
    public Producto crear(Producto producto) {
        return productoRepository.save(producto);
    }

    // Actualizar un producto existente
    public Producto actualizar(Long id, Producto datos) {
        return productoRepository.findById(id).map(existing -> {
            existing.setNombre(datos.getNombre());
            existing.setDescripcion(datos.getDescripcion());
            existing.setPrecio(datos.getPrecio());
            // Ya no es necesario el campo de cliente, lo quitamos
            return productoRepository.save(existing);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado"));
    }

    // Eliminar un producto por su ID
    public void eliminar(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado");
        }
        productoRepository.deleteById(id);
    }
}
