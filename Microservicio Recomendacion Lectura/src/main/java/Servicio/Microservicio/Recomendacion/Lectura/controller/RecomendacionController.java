package Servicio.Microservicio.Recomendacion.Lectura.controller;

import Servicio.Microservicio.Recomendacion.Lectura.model.Recomendacion;
import Servicio.Microservicio.Recomendacion.Lectura.service.RecomendacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/recomendaciones")
public class RecomendacionController {

    @Autowired
    private RecomendacionService service;

    @PostMapping
    public ResponseEntity<Recomendacion> crear(@RequestBody Recomendacion r) {
        return ResponseEntity.ok(service.guardar(r));
    }

    @GetMapping
    public List<Recomendacion> obtenerTodas() {
        return service.obtenerTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recomendacion> obtenerPorId(@PathVariable int id) {
        Optional<Recomendacion> r = service.obtenerPorId(id);
        return r.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/categoria/{categoria}")
    public List<Recomendacion> obtenerPorCategoria(@PathVariable String categoria) {
        return service.obtenerPorCategoria(categoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        service.eliminar(id);
        return ResponseEntity.ok().build();
    }
}
