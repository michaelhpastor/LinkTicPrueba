package com.empresa.prueba.inventory.controller;

import com.empresa.prueba.inventory.entity.Inventario;
import com.empresa.prueba.inventory.service.InventarioService;
import lombok.Data;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/inventarios", produces = "application/vnd.api+json")
public class InventarioController {

    private final InventarioService service;
    public InventarioController(InventarioService service) { this.service = service; }

    @GetMapping("/{productoId}")
    public ResponseEntity<Object> getCantidad(@PathVariable Long productoId) {
        Integer qty = service.obtenerCantidad(productoId);
        return ResponseEntity.ok(java.util.Map.of(
                "data", java.util.Map.of(
                        "type","inventarios",
                        "id", String.valueOf(productoId),
                        "attributes", java.util.Map.of("producto_id",productoId,"cantidad",qty)
                )
        ));
    }

    // endpoint para reducir stock tras compra (body: {"quantity":2})
    @PostMapping(value="/{productoId}/purchase", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> purchase(@PathVariable Long productoId, @RequestBody PurchaseRequest req) {
        Inventario updated = service.actualizarCantidadTrasCompra(productoId, req.quantity);
        return ResponseEntity.ok(java.util.Map.of(
                "data", java.util.Map.of(
                        "type","inventarios",
                        "id", String.valueOf(productoId),
                        "attributes", java.util.Map.of("producto_id",productoId,"cantidad",updated.getCantidad())
                )
        ));
    }

    @PostMapping(value="/{productoId}/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> create(@PathVariable Long productoId, @RequestBody PurchaseRequest req) {
        Inventario created = service.crearSiNoExiste(productoId, req.quantity);
        return ResponseEntity.status(201).body(java.util.Map.of("data", java.util.Map.of(
                "type","inventarios",
                "id", String.valueOf(productoId),
                "attributes", java.util.Map.of("producto_id",productoId,"cantidad",created.getCantidad())
        )));
    }

    @Data
    static class PurchaseRequest {
        public int quantity;
    }
}