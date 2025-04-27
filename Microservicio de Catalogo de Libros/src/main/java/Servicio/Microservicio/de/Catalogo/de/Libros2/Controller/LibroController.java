package Servicio.Microservicio.de.Catalogo.de.Libros2.Controller;

import Servicio.Microservicio.de.Catalogo.de.Libros2.Model.Libro;
import Servicio.Microservicio.de.Catalogo.de.Libros2.Service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;

    // Obtener todos los libros
    @GetMapping
    public List<Libro> listarLibros() {
        return libroService.getLibros();
    }

    // Agregar un nuevo libro
    @PostMapping
    public Libro agregarLibro(@RequestBody Libro libro) {
        return libroService.saveLibro(libro);
    }

    // Buscar un libro por ID
    @GetMapping("/{id}")
    public Libro buscarLibro(@PathVariable int id) {
        return libroService.getLibroById(id);
    }

    // Actualizar un libro existente
    @PutMapping("/{id}")
    public Libro actualizarLibro(@PathVariable int id, @RequestBody Libro libro) {
        libro.setIdLibro(id);
        return libroService.updateLibro(libro);
    }

    // Eliminar un libro por su ID
    @DeleteMapping("/{id}")
    public String eliminarLibro(@PathVariable int id) {
        return libroService.deleteLibro(id);
    }
}
