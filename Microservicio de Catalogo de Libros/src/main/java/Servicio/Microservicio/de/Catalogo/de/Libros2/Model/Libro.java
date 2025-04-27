package Servicio.Microservicio.de.Catalogo.de.Libros2.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Libro {
    private int idLibro;             // ID único del libro
    private String titulo;           // Título del libro
    private String autor;            // Nombre del autor
    private String editorial;        // Editorial del libro
    private int anioPublicacion;      // Año de publicación
    private String categoria;         // Categoría o género del libro
    private String estado;            // Estado del libro (ej: disponible, prestado, dañado)
}
