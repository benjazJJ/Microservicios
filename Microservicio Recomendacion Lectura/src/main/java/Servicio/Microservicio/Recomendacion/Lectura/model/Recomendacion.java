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
    private int id;

    private String tituloLibro;
    private String autor;
    private String categoria;
    private String motivo;
}
