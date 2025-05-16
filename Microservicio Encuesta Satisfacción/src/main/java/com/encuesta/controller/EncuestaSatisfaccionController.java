package com.encuesta.controller;

import com.encuesta.model.EncuestaSatisfaccion;
import com.encuesta.service.EncuestaSatisfaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/encuestas")
public class EncuestaSatisfaccionController {

    @Autowired
    private EncuestaSatisfaccionService service;

    @PostMapping
    public ResponseEntity<EncuestaSatisfaccion> crear(@Valid @RequestBody EncuestaSatisfaccion encuesta) {
        return ResponseEntity.ok(service.crear(encuesta));
    }

    @GetMapping
    public ResponseEntity<List<EncuestaSatisfaccion>> obtenerTodas() {
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
