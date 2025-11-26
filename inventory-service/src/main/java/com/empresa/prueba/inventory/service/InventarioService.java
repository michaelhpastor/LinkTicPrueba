package com.empresa.prueba.inventory.service;

import com.empresa.prueba.inventory.entity.Inventario;

public interface InventarioService {
    Integer obtenerCantidad(Long productoId);
    Inventario actualizarCantidadTrasCompra(Long productoId, int cantidadComprada);
    Inventario crearSiNoExiste(Long productoId, int cantidadInicial);
}