package Servicio.Microservicio.de.Catalogo.de.Libros2.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // Marca esta clase como una entidad JPA
@Table(name = "libro") // Nombre de la tabla en la base de datos
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID autoincremental
    @Column(name = "id_libro")
    private int idLibro;

    @Column(name = "titulo", nullable = false, length = 150)
    private String titulo;

    @Column(name = "autor", nullable = false, length = 100)
    private String autor;

    @Column(name = "editorial", length = 100)
    private String editorial;

    @Column(name = "anio_publicacion")
    private int anioPublicacion;

    @Column(name = "categoria", length = 50)
    private String categoria;

    @Column(name = "estado", length = 30)
    private String estado;
}
