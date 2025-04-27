package com.tuproducto.productos;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductoController {

    @GetMapping("/productos")
    public String obtenerProductos() {
        return "¡Hola, productos!";
    }
}
