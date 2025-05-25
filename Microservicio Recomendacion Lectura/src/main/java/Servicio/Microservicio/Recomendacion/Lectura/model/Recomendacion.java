package Servicio.Microservicio.Recomendacion.Lectura.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "recomendacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recomendacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "titulo_libro")
    private String tituloLibro;

    @Column(name = "autor")
    private String autor;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "motivo")
    private String motivo;
}
