package com.empresa.prueba.inventory.dto;

import lombok.Data;

@Data
public class ProductoDTO {
    private Long id;
    private String name;
    private Double price;
}