package com.empresa.prueba.inventory.client;

import com.empresa.prueba.inventory.dto.ProductoDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProductosClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${productos.service.url}")
    private String productosUrl; // e.g. http://localhost:8081/api

    @Value("${productos.service.api-key}")
    private String apiKey;

    public ProductoDTO obtenerProducto(Long id) {
        String url = productosUrl + "/products/" + id;
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-KEY", apiKey);
        headers.setAccept(java.util.List.of(MediaType.valueOf("application/vnd.api+json")));
        HttpEntity<Void> request = new HttpEntity<>(headers);
        ResponseEntity<ProductoDTO> resp = restTemplate.exchange(url, HttpMethod.GET, request, ProductoDTO.class);
        return resp.getBody();
    }
}
