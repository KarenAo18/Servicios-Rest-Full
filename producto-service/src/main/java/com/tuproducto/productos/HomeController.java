package com.tuproducto.productos;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "¡Bienvenido al Servicio de Productos! Usa /api/productos para gestionar productos.";
    }
}