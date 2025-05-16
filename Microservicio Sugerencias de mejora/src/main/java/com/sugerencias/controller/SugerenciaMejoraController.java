package com.sugerencias.controller;

import com.sugerencias.model.SugerenciaMejora;
import com.sugerencias.service.SugerenciaMejoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sugerencias")
public class SugerenciaMejoraController {

    @Autowired
    private SugerenciaMejoraService service;

    @PostMapping
    public ResponseEntity<SugerenciaMejora> crear(@RequestBody SugerenciaMejora sugerencia) {
        return ResponseEntity.ok(service.crear(sugerencia));
    }

    @GetMapping
    public ResponseEntity<List<SugerenciaMejora>> obtenerTodas() {
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
