package Microservicio.Recomendaciones.y.Sugerencias.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Microservicio.Recomendaciones.y.Sugerencias.model.RecomendacionesySugerencias;
import Microservicio.Recomendaciones.y.Sugerencias.service.RecomendacionService;

@RestController
@RequestMapping("/api/v1/sugerencias")
public class RecomendacionController {

    @Autowired
    private RecomendacionService service;

    // Crear nueva sugerencia con validación
    @PostMapping
    public ResponseEntity<?> crearSugerencia(@RequestBody Map<String, Object> datos) {
        try {
            String correo = (String) datos.get("correo");
            String contrasena = (String) datos.get("contrasena");

            RecomendacionesySugerencias sugerencia = service.crearRecomendacionSiEsValida(datos, correo, contrasena);
            return ResponseEntity.status(201).body(sugerencia);
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", e.getMessage());
            return ResponseEntity.status(401).body(error);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", e.getMessage());
            return ResponseEntity.status(400).body(error);
        }
    }

    // Obtener todas las sugerencias
    @GetMapping
    public ResponseEntity<List<RecomendacionesySugerencias>> obtenerSugerencias() {
        List<RecomendacionesySugerencias> sugerencias = service.obtenerTodas();
        return ResponseEntity.ok(sugerencias);
    }

    // Obtener sugerencia por ID
    @GetMapping("/{id}")
    public ResponseEntity<RecomendacionesySugerencias> obtenerSugerenciaPorId(@PathVariable Integer id) {
        Optional<RecomendacionesySugerencias> sugerenciaOpt = Optional.ofNullable(service.obtenerPorId(id));
        return sugerenciaOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Actualizar sugerencia
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> actualizarSugerencia(@PathVariable Integer id,
            @RequestBody Map<String, Object> datos) {
        RecomendacionesySugerencias sugerenciaExistente = service.obtenerPorId(id);
        if (sugerenciaExistente == null) {
            return ResponseEntity.notFound().build();
        }

        try {
            String nuevoMensaje = datos.get("mensaje").toString();
            int nuevaPuntuacion = Integer.parseInt(datos.get("puntuacion").toString());

            sugerenciaExistente.setMensaje(nuevoMensaje);
            sugerenciaExistente.setPuntuacion(nuevaPuntuacion);

            RecomendacionesySugerencias actualizada = service.actualizarRecomendacion(sugerenciaExistente);

            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("mensaje", "Edición de sugerencia realizada con éxito.");
            respuesta.put("sugerencia", actualizada);

            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", "Error al actualizar la sugerencia: " + e.getMessage());
            return ResponseEntity.status(400).body(Map.of("error", e.getMessage()));
        }
    }

    // Eliminar sugerencia por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminarSugerencia(@PathVariable Integer id) {
        if (service.obtenerPorId(id) == null) {
            return ResponseEntity.notFound().build();
        }
        service.eliminarPorId(id);
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Sugerencia eliminada con éxito");
        return ResponseEntity.ok(response);
    }
}
