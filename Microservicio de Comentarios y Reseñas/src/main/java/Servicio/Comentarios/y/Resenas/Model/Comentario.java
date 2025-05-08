package Servicio.Comentarios.y.Resenas.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "reseña")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comentario")
    private int idComentario;

    @Column(name = "nombre_libro", nullable = false, length = 100)
    private String nombreLibro;

    @Column(name = "comentario", nullable = false, length = 750)
    private String comentario;

    @Column(name = "fecha_comentario", nullable = false)
    private Date fechaComentario;
}