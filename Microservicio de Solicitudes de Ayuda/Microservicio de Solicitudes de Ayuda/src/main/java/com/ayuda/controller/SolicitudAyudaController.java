package com.ayuda.controller;

import com.ayuda.model.SolicitudAyuda;
import com.ayuda.service.SolicitudAyudaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/ayuda")
public class SolicitudAyudaController {

    @Autowired
    private SolicitudAyudaService service;

    // GET: todos los roles pueden acceder
    @GetMapping
    public ResponseEntity<List<SolicitudAyuda>> obtenerTodas() {
        return ResponseEntity.ok(service.obtenerTodas());
    }

    // GET por ID: todos los roles pueden acceder
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable int id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET filtrado por correo: todos los roles pueden acceder
    @GetMapping("/usuario/{correo}")
    public ResponseEntity<List<SolicitudAyuda>> obtenerPorCorreo(@PathVariable String correo) {
        return ResponseEntity.ok(service.obtenerPorCorreoUsuario(correo));
    }

    // POST: solo ESTUDIANTE o DOCENTE
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Map<String, Object> body) {
        try {
            String correo = (String) body.get("correo");
            String contrasena = (String) body.get("contrasena");
            String asunto = (String) body.get("asunto");
            String mensaje = (String) body.get("mensaje");

            SolicitudAyuda creada = service.crear(asunto, mensaje, correo, contrasena);
            return ResponseEntity.status(201).body(creada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        }
    }

    // PUT: solo ADMINISTRADOR o BIBLIOTECARIO
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable int id, @RequestBody Map<String, Object> body) {
        try {
            String correo = (String) body.get("correo");
            String contrasena = (String) body.get("contrasena");
            String asunto = (String) body.get("asunto");
            String mensaje = (String) body.get("mensaje");

            SolicitudAyuda actualizada = service.actualizar(id, asunto, mensaje, correo, contrasena);
            return ResponseEntity.ok(actualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        }
    }

    // DELETE: solo ADMINISTRADOR o BIBLIOTECARIO
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPorId(@PathVariable int id, @RequestBody Map<String, String> body) {
        try {
            String correo = body.get("correo");
            String contrasena = body.get("contrasena");

            service.eliminarPorId(id, correo, contrasena);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        }
    }
}
