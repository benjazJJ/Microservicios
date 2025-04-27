package Servicio.Microservicio.de.Catalogo.de.Libros2.Repository;

import Servicio.Microservicio.de.Catalogo.de.Libros2.Model.Libro;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LibroRepository {

    private final List<Libro> listaLibros = new ArrayList<>();

    // Constructor: agrega libros de ejemplo al catálogo
    public LibroRepository() {
        listaLibros.add(new Libro(1, "Cien Años de Soledad", "Gabriel García Márquez", "Editorial Sudamericana", 1967, "Novela", "Disponible"));
        listaLibros.add(new Libro(2, "El Principito", "Antoine de Saint-Exupéry", "Editorial Salamandra", 1943, "Infantil", "Disponible"));
    }

    // Devuelve todos los libros del catálogo
    public List<Libro> obtenerLibros() {
        return new ArrayList<>(listaLibros);
    }

    // Busca un libro por su ID
    public Libro buscarPorId(int idLibro) {
        for (Libro libro : listaLibros) {
            if (libro.getIdLibro() == idLibro) {
                return libro;
            }
        }
        return null;
    }

    // Guarda un nuevo libro, validando que no exista un ID duplicado
    public Libro guardar(Libro libro) {
        if (buscarPorId(libro.getIdLibro()) != null) {
            throw new IllegalArgumentException("Ya existe un libro con el ID " + libro.getIdLibro());
        }
        listaLibros.add(libro);
        return libro;
    }

    // Actualiza un libro existente, validando que el ID exista primero
    public Libro actualizar(Libro libro) {
        Libro existente = buscarPorId(libro.getIdLibro());
        if (existente == null) {
            throw new IllegalArgumentException("No se puede actualizar porque no existe un libro con ID " + libro.getIdLibro());
        }
        eliminar(libro.getIdLibro());
        listaLibros.add(libro);
        return libro;
    }

    // Elimina un libro por su ID, validando que exista
    public void eliminar(int idLibro) {
        Libro libroEncontrado = buscarPorId(idLibro);
        if (libroEncontrado == null) {
            throw new IllegalArgumentException("No se puede eliminar porque no existe un libro con ID " + idLibro);
        }
        listaLibros.remove(libroEncontrado);
    }
}
