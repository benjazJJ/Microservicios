package Servicio.Microservicio.de.Administracion.de.Contenidos.y.Multimedia4.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity // Indica que esta clase será una tabla en la base de datos
@Table(name = "multimedia") // Nombre de la tabla
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Multimedia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremental
    @Column(name = "id_multimedia")
    private int idMultimedia; // ID único del anuncio o publicación

    @Column(name = "titulo", nullable = false, length = 100)
    private String titulo; // Título del anuncio

    @Column(name = "descripcion", length = 255)
    private String descripcion; // Descripción detallada del anuncio

    @Column(name = "url_imagen", length = 255)
    private String urlImagen; // URL de imagen principal

    @Column(name = "url_video", length = 255)
    private String urlVideo; // URL de video relacionado

    @Column(name = "fecha_publicacion", nullable = false)
    private Date fechaPublicacion; // Fecha de publicación

    @Column(name = "fecha_expiracion")
    private Date fechaExpiracion; // Fecha en la que deja de mostrarse

    @Column(name = "tipo_publicacion", length = 50)
    private String tipoPublicacion; // Tipo de publicación (evento, aviso, promoción)
}
