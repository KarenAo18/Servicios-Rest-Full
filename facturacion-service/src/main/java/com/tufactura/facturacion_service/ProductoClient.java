package com.tufactura.facturacion_service;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProductoClient {

    private final RestTemplate restTemplate;

    public ProductoClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ProductoDTO obtenerProductoPorId(Long id) {
        String url = "http://producto-service/productos/" + id;
        return restTemplate.getForObject(url, ProductoDTO.class);
    }
}
