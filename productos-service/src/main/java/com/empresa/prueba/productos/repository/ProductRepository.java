package com.empresa.prueba.productos.repository;

import com.empresa.prueba.productos.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}