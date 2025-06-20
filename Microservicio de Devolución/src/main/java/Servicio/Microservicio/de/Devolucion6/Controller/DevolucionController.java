package Servicio.Microservicio.de.Devolucion6.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Servicio.Microservicio.de.Devolucion6.Model.Devolucion;
import Servicio.Microservicio.de.Devolucion6.Service.DevolucionService;

@RestController
@RequestMapping("/api/v1/devoluciones")
public class DevolucionController {

    @Autowired
    private DevolucionService devolucionService;
    //ESTE METODO YA FUÉ TESTEADO
    // GET: todos pueden acceder
    @GetMapping
    public ResponseEntity<List<Devolucion>> listar() {
        List<Devolucion> devolucion = devolucionService.listarDevoluciones();
        if (devolucion.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(devolucion);
    }

    // POST: solo usuarios y docentes
    @PostMapping
    public ResponseEntity<?> crearnuevaDevolucion(@RequestBody Map<String, Object> body) {
        try {
            Devolucion nuevaDevolucion = new Devolucion();
            nuevaDevolucion.setFechaDevolucion(java.sql.Date.valueOf(body.get("fechaDevolucion").toString()));
            nuevaDevolucion.setEstadoLibro(body.get("estadoLibro").toString());
            nuevaDevolucion.setObservaciones(body.get("observaciones").toString());
            nuevaDevolucion.setIdPrestamo(Integer.parseInt(body.get("idPrestamo").toString()));

            String correo = body.get("correo").toString();
            String contrasena = body.get("contrasena").toString();

            Devolucion devolucion = devolucionService.crearDevolucion(nuevaDevolucion, correo, contrasena);
            return ResponseEntity.status(201).body(devolucion);
        } catch (RuntimeException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        }
    }
    //ESTE METODO YA FUÉ TESTEADO
    // GET por ID: todos pueden acceder
    @GetMapping("/{id}")
    public ResponseEntity<Devolucion> buscarDevolucionPorId(@PathVariable Integer id) {
        try {
            Devolucion dev = devolucionService.buscarDevolucionPorID(id);
            return ResponseEntity.ok(dev);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE: solo administrador o bibliotecario
    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarDevolucion(@PathVariable int id, @RequestBody Map<String, Object> body) {
        try {
            String correo = body.get("correo").toString();
            String contrasena = body.get("contrasena").toString();

            devolucionService.validarAdministradorOBibliotecario(correo, contrasena);
            devolucionService.borrarDevolucion(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        }
    }

    // PUT: solo administrador
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarDevolucionPorID(@PathVariable Integer id, @RequestBody Map<String, Object> body) {
        try {
            String correo = body.get("correo").toString();
            String contrasena = body.get("contrasena").toString();

            Devolucion dev = new Devolucion();
            dev.setFechaDevolucion(java.sql.Date.valueOf(body.get("fechaDevolucion").toString()));
            dev.setEstadoLibro(body.get("estadoLibro").toString());
            dev.setObservaciones(body.get("observaciones").toString());

            Devolucion actualizada = devolucionService.actualizarDevolucion(id, dev, correo, contrasena);
            return ResponseEntity.ok(actualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        }
    }
}
