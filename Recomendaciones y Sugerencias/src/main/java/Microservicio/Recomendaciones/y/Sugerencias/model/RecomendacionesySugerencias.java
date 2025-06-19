package Microservicio.Recomendaciones.y.Sugerencias.model;
import java.sql.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Recomendaciones_Sugerencias")
public class RecomendacionesySugerencias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Encuesta", unique = true, nullable = false)
    private Integer idEncuesta;

    @Column(name = "id_usuario", unique = true, nullable = false)
    private Integer idUsuario;

   @Column(name = "correo_usuario", nullable = false, length = 100)
    private String correo;

    @Column(name = "contrasena_usuario", nullable = false, length = 100)
    private String contrasena;

    @Column(name = "mensaje", nullable = false, length = 500)
    private String mensaje;

    @Column(name = "fecha_envio")
    private Date fechaEnvio;

    @Min(1)
    @Max(5)
    @Column(name = "puntuacion", nullable = false)
    private int puntuacion;

}
