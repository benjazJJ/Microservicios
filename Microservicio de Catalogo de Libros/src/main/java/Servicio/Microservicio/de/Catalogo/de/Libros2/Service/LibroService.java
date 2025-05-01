package Servicio.Microservicio.de.Catalogo.de.Libros2.Service;

import Servicio.Microservicio.de.Catalogo.de.Libros2.Model.Libro;
import Servicio.Microservicio.de.Catalogo.de.Libros2.Repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    // Obtener todos los libros
    public List<Libro> getLibros() {
        return libroRepository.findAll();
    }

    // Guardar un nuevo libro
    public Libro saveLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    // Buscar un libro por ID
    public Libro getLibroById(int idLibro) {
        Optional<Libro> libro = libroRepository.findById(idLibro);
        return libro.orElse(null); // Devuelve null si no existe
    }

    // Actualizar un libro (usa save, porque JPA detecta si existe y actualiza)
    public Libro updateLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    // Eliminar un libro por ID (verifica existencia antes)
    public String deleteLibro(int idLibro) {
        if (libroRepository.existsById(idLibro)) {
            libroRepository.deleteById(idLibro);
            return "Libro eliminado correctamente";
        } else {
            return "Libro no encontrado";
        }
    }
}

