package Microservicio.Recomendaciones.y.Sugerencias.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Microservicio.Recomendaciones.y.Sugerencias.model.RecomendacionesySugerencias;
import Microservicio.Recomendaciones.y.Sugerencias.service.RecomendacionService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sugerencias")
public class RecomendacionController {

    @Autowired
    private RecomendacionService service;

    @PostMapping
    public ResponseEntity<RecomendacionesySugerencias> crear(@RequestBody RecomendacionesySugerencias sugerencia) {
        return ResponseEntity.ok(service.crear(sugerencia));
    }

    @GetMapping
    public ResponseEntity<List<RecomendacionesySugerencias>> obtenerTodas() {
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
