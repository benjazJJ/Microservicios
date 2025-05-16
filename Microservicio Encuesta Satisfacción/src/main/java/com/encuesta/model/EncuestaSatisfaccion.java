package com.encuesta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "encuestas_satisfaccion")
public class EncuestaSatisfaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_encuesta")
    private int idEncuesta;

    @Column(name = "correo_usuario", nullable = false, length = 100)
    private String correoUsuario;

    @Min(1)
    @Max(5)
    @Column(name = "puntuacion", nullable = false)
    private int puntuacion;

    @Column(name = "comentario", length = 500)
    private String comentario;

    @Column(name = "fecha")
    private LocalDateTime fecha;
}
