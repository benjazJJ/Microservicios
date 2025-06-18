package Servicio.Microservicio.Recomendacion.Lectura.controller;

import Servicio.Microservicio.Recomendacion.Lectura.model.Recomendacion;
import Servicio.Microservicio.Recomendacion.Lectura.service.RecomendacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/recomendaciones")
public class RecomendacionController {

    @Autowired
    private RecomendacionService service;

    // POST solo para ESTUDIANTE o DOCENTE
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Map<String, Object> datos) {
        try {
            String correo = datos.get("correo").toString();
            String contrasena = datos.get("contrasena").toString();

            Recomendacion r = new Recomendacion();
            r.setTituloLibro(datos.get("tituloLibro").toString());
            r.setAutor(datos.get("autor").toString());
            r.setCategoria(datos.get("categoria").toString());
            r.setMotivo(datos.get("motivo").toString());

            return ResponseEntity.ok(service.guardar(r, correo, contrasena));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // GET todos (público)
    @GetMapping
    public List<Recomendacion> obtenerTodas() {
        return service.obtenerTodas();
    }

    // GET por ID (público)
    @GetMapping("/{id}")
    public ResponseEntity<Recomendacion> obtenerPorId(@PathVariable int id) {
        Optional<Recomendacion> r = service.obtenerPorId(id);
        return r.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // GET por categoría (público)
    @GetMapping("/categoria/{categoria}")
    public List<Recomendacion> obtenerPorCategoria(@PathVariable String categoria) {
        return service.obtenerPorCategoria(categoria);
    }

    // DELETE solo para ADMIN o BIBLIOTECARIO
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(
            @PathVariable int id,
            @RequestBody Map<String, String> credenciales) {
        try {
            String correo = credenciales.get("correo");
            String contrasena = credenciales.get("contrasena");

            service.eliminar(id, correo, contrasena);
            return ResponseEntity.ok("Recomendación eliminada correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(403).body(e.getMessage());
        }
    }
}
