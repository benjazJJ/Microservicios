package com.example.Microservicio.de.Stock.Controller;

import java.util.Map;
import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Microservicio.de.Stock.Model.LibroStock;
import com.example.Microservicio.de.Stock.Repository.EstadoLibroRepository;
import com.example.Microservicio.de.Stock.Repository.LibroStockRepository;
import com.example.Microservicio.de.Stock.Service.LibroStockService;
import com.example.Microservicio.de.Stock.Service.ValidacionResponse;

@RestController
@RequestMapping("/api/v1/librostock")
public class LibroStockController {

    @Autowired
    private LibroStockRepository libroStockRepository;

    @Autowired
    private EstadoLibroRepository estadoLibroRepository;

    @Autowired
    private LibroStockService libroStockService;

    private boolean noTienePermiso(ValidacionResponse validacion) {
        return !validacion.isAutenticado() ||
                validacion.getRol().equalsIgnoreCase("ESTUDIANTE") ||
                validacion.getRol().equalsIgnoreCase("DOCENTE");
    }

    // GET público - ver todo el stock
    @GetMapping
    public ResponseEntity<List<LibroStock>> obtenerLibroStock() {
        return ResponseEntity.ok(libroStockRepository.findAll());
    }

    // GET público - ver libro por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerLibroPorId(@PathVariable Long id) {
        Optional<LibroStock> libroStock = libroStockRepository.findById(id);
        return libroStock.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearLibroStock(@RequestBody Map<String, Object> datos) {
        String correo = datos.get("correo").toString();
        String contrasena = datos.get("contrasena").toString();
        ValidacionResponse validacion = libroStockService.validarUsuario(correo, contrasena);
        if (noTienePermiso(validacion)) {
            return ResponseEntity.status(403).body("Acceso denegado.");
        }

        LibroStock nuevoLibro = libroStockService.mapToLibroStock(datos);

        // Buscar si ya existe un libro con ese nombre
        Optional<LibroStock> libroExistenteOpt = libroStockRepository.findAll().stream()
                .filter(l -> l.getNombreLibro().equalsIgnoreCase(nuevoLibro.getNombreLibro()))
                .findFirst();

        if (libroExistenteOpt.isPresent()) {
            LibroStock existente = libroExistenteOpt.get();
            existente.setCantidad(existente.getCantidad() + nuevoLibro.getCantidad());
            return ResponseEntity.ok(libroStockRepository.save(existente));
        } else {
            return ResponseEntity.ok(libroStockRepository.save(nuevoLibro));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id, @RequestBody Map<String, String> datos) {
        ValidacionResponse validacion = libroStockService.validarUsuario(datos.get("correo"), datos.get("contrasena"));
        if (noTienePermiso(validacion)) {
            return ResponseEntity.status(403).body("Acceso denegado.");
        }

        if (libroStockRepository.existsById(id)) {
            libroStockRepository.deleteById(id);
            return ResponseEntity.ok("Libro eliminado del stock");
        } else {
            return ResponseEntity.status(404).body("Libro no encontrado");
        }
    }

    @PutMapping("/actualizar-stock/{id}")
    public ResponseEntity<?> actualizarCantidad(@PathVariable Long id, @RequestBody Map<String, Integer> datos) {
        Optional<LibroStock> libroOpt = libroStockRepository.findById(id);

        if (libroOpt.isEmpty()) {
            return ResponseEntity.status(404).body("Libro no encontrado");
        }

        LibroStock libro = libroOpt.get();
        int nuevaCantidad = datos.getOrDefault("cantidad", -1);

        if (nuevaCantidad < 0) {
            return ResponseEntity.badRequest().body("Cantidad inválida");
        }

        libro.setCantidad(nuevaCantidad);
        libroStockRepository.save(libro);
        return ResponseEntity.ok("Cantidad actualizada correctamente");
    }

}
