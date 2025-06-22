package com.ayuda.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Entity
@Table(name = "solicitudes_ayuda")
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudAyuda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitud")
    private int idSolicitud;

    @Column(name = "correo_usuario", nullable = false, length = 100)
    private String correoUsuario;

    @Column(name = "asunto", nullable = false, length = 100)
    private String asunto;

    @Column(name = "mensaje", nullable = false, length = 500)
    private String mensaje;

    @Column(name = "fecha_envio")
    private Date fechaEnvio;
}
