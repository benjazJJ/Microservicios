package Servicio.Microservicio.de.Catalogo.de.Libros2.Service;

import Servicio.Microservicio.de.Catalogo.de.Libros2.Model.Libro;
import Servicio.Microservicio.de.Catalogo.de.Libros2.Repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    // Obtener todos los libros
    public List<Libro> getLibros() {
        return libroRepository.obtenerLibros();
    }

    // Guardar un nuevo libro
    public Libro saveLibro(Libro libro) {
        return libroRepository.guardar(libro);
    }

    // Buscar un libro por ID
    public Libro getLibroById(int idLibro) {
        return libroRepository.buscarPorId(idLibro);
    }

    // Actualizar un libro existente
    public Libro updateLibro(Libro libro) {
        return libroRepository.actualizar(libro);
    }

    // Eliminar un libro por su ID
    public String deleteLibro(int idLibro) {
        libroRepository.eliminar(idLibro);
        return "Libro eliminado correctamente";
    }
}
