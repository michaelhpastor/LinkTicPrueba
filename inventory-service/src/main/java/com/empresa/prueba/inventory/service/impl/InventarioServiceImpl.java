package com.empresa.prueba.inventory.service.impl;

import com.empresa.prueba.inventory.client.ProductosClient;
import com.empresa.prueba.inventory.entity.Inventario;
import com.empresa.prueba.inventory.repository.InventarioRepository;
import com.empresa.prueba.inventory.service.InventarioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventarioServiceImpl implements InventarioService {

    private final InventarioRepository repo;
    private final ProductosClient productosClient;

    public InventarioServiceImpl(InventarioRepository repo, ProductosClient productosClient) {
        this.repo = repo;
        this.productosClient = productosClient;
    }

    @Override
    public Integer obtenerCantidad(Long productoId) {
        // validar producto remoto (lanza excepción si no existe)
        productosClient.obtenerProducto(productoId);
        return repo.findById(productoId).map(Inventario::getCantidad).orElse(0);
    }

    @Override
    @Transactional
    public Inventario actualizarCantidadTrasCompra(Long productoId, int cantidadComprada) {
        productosClient.obtenerProducto(productoId); // validar

        Inventario inv = repo.findById(productoId).orElse(new Inventario(productoId, 0));
        int nueva = inv.getCantidad() - cantidadComprada;
        if (nueva < 0) throw new RuntimeException("Stock insuficiente");
        inv.setCantidad(nueva);
        Inventario saved = repo.save(inv);
        System.out.println("EVENT: INVENTORY_CHANGED productId=" + productoId + " newQty=" + nueva);
        return saved;
    }

    @Override
    public Inventario crearSiNoExiste(Long productoId, int cantidadInicial) {
        if (repo.existsById(productoId)) return repo.findById(productoId).get();
        Inventario inv = new Inventario(productoId, cantidadInicial);
        return repo.save(inv);
    }
}