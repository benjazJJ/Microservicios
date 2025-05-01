package Servicio.Microservicio.de.Catalogo.de.Libros2.Controller;

import Servicio.Microservicio.de.Catalogo.de.Libros2.Model.Libro;
import Servicio.Microservicio.de.Catalogo.de.Libros2.Service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;

    // Obtener todos los libros
    @GetMapping
    public ResponseEntity<List<Libro>> listarLibros() {
        List<Libro> libros = libroService.getLibros();
        if (libros.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(libros);
    }

    // Agregar un nuevo libro
    @PostMapping
    public ResponseEntity<Libro> agregarLibro(@RequestBody Libro libro) {
        Libro nuevo = libroService.saveLibro(libro);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    // Buscar un libro por ID
    @GetMapping("/{id}")
    public ResponseEntity<Libro> buscarLibro(@PathVariable int id) {
        Libro libro = libroService.getLibroById(id);
        if (libro != null) {
            return ResponseEntity.ok(libro);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Actualizar un libro existente
    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizarLibro(@PathVariable int id, @RequestBody Libro libro) {
        libro.setIdLibro(id);
        Libro actualizado = libroService.updateLibro(libro);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar un libro por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarLibro(@PathVariable int id) {
        String mensaje = libroService.deleteLibro(id);
        if (mensaje.contains("correctamente")) {
            return ResponseEntity.ok(mensaje);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
        }
    }
}
