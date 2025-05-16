package com.sugerencias.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sugerencias_mejora")
public class SugerenciaMejora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sugerencia")
    private int idSugerencia;

    @Column(name = "correo_usuario", nullable = false, length = 100)
    private String correoUsuario;

    @Column(name = "mensaje", nullable = false, length = 500)
    private String mensaje;

    @Column(name = "fecha_envio")
    private LocalDateTime fechaEnvio;
}

