package com.ayuda.controller;

import com.ayuda.model.SolicitudAyuda;
import com.ayuda.service.SolicitudAyudaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ayuda")
public class SolicitudAyudaController {

    @Autowired
    private SolicitudAyudaService service;

    @PostMapping
    public ResponseEntity<SolicitudAyuda> crear(@RequestBody SolicitudAyuda solicitud) {
        return ResponseEntity.ok(service.crear(solicitud));
    }

    @GetMapping
    public ResponseEntity<List<SolicitudAyuda>> obtenerTodas() {
        return ResponseEntity.ok(service.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable int id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPorId(@PathVariable int id) {
        service.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
