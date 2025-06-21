package com.example.Microservicio.de.Stock.Controller;

import java.util.Map;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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

    // GET público: ver todos los libros en stock con HATEOAS
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<LibroStock>>> obtenerLibroStock() {
        List<LibroStock> lista = libroStockRepository.findAll();

        List<EntityModel<LibroStock>> recursos = lista.stream()
            .map(libro -> EntityModel.of(libro,
                WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(LibroStockController.class)
                        .obtenerLibroPorId(libro.getIdLibroStock())
                ).withSelfRel()
            ))
            .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(recursos));
    }

    // GET público: obtener un libro por su ID con HATEOAS
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerLibroPorId(@PathVariable Long id) {
        return libroStockRepository.findById(id)
            .map(libro -> {
                EntityModel<LibroStock> recurso = EntityModel.of(libro);
                recurso.add(WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(LibroStockController.class)
                                .obtenerLibroPorId(id)).withSelfRel());
                recurso.add(WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(LibroStockController.class)
                                .obtenerLibroStock()).withRel("todos"));
                return ResponseEntity.ok(recurso);
            })
            .orElse(ResponseEntity.notFound().build());
    }

    // POST con validación: crear o aumentar libro, con retorno HATEOAS
    @PostMapping("/crear")
    public ResponseEntity<?> crearLibroStock(@RequestBody Map<String, Object> datos) {
        String correo = datos.get("correo").toString();
        String contrasena = datos.get("contrasena").toString();
        ValidacionResponse validacion = libroStockService.validarUsuario(correo, contrasena);

        if (noTienePermiso(validacion)) {
            return ResponseEntity.status(403).body("Acceso denegado.");
        }

        LibroStock nuevoLibro = libroStockService.mapToLibroStock(datos);

        Optional<LibroStock> libroExistenteOpt = libroStockRepository.findAll().stream()
                .filter(l -> l.getNombreLibro().equalsIgnoreCase(nuevoLibro.getNombreLibro()))
                .findFirst();

        LibroStock resultado;
        if (libroExistenteOpt.isPresent()) {
            LibroStock existente = libroExistenteOpt.get();
            existente.setCantidad(existente.getCantidad() + nuevoLibro.getCantidad());
            resultado = libroStockRepository.save(existente);
        } else {
            resultado = libroStockRepository.save(nuevoLibro);
        }

        EntityModel<LibroStock> recurso = EntityModel.of(resultado);
        recurso.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(LibroStockController.class)
                        .obtenerLibroPorId(resultado.getIdLibroStock())).withSelfRel());
        recurso.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(LibroStockController.class)
                        .obtenerLibroStock()).withRel("todos"));

        return ResponseEntity.ok(recurso);
    }

    // DELETE con validación
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

    // PUT público: solo actualiza la cantidad, no requiere HATEOAS
    @PutMapping("/actualizar-stock/{id}")
    public ResponseEntity<?> actualizarCantidad(@PathVariable Long id, @RequestBody Map<String, Integer> datos) {
        Optional<LibroStock> libroOpt = libroStockRepository.findById(id);

        if (libroOpt.isEmpty()) {
            return ResponseEntity.status(404).body("Libro no encontrado");
        }

        int nuevaCantidad = datos.getOrDefault("cantidad", -1);
        if (nuevaCantidad < 0) {
            return ResponseEntity.badRequest().body("Cantidad inválida");
        }

        LibroStock libro = libroOpt.get();
        libro.setCantidad(nuevaCantidad);
        libroStockRepository.save(libro);

        return ResponseEntity.ok("Cantidad actualizada correctamente");
    }
}
