package com.empresa.prueba.inventory.repository;

import com.empresa.prueba.inventory.entity.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {
}