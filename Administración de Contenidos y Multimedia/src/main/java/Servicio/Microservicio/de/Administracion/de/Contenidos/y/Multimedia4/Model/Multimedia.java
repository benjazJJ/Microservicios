package Servicio.Microservicio.de.Administracion.de.Contenidos.y.Multimedia4.Model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//LES DEJO LOS COMENTARIOS PARA QUE SEPAN QUE HACE CADA COSA
// BY: Benjamín Palma

@Data // Lombok: genera getters, setters, toString, equals y hashCode automáticamente
@NoArgsConstructor // Lombok: genera un constructor vacío
@AllArgsConstructor // Lombok: genera un constructor con todos los atributos

public class Multimedia {
    private int idMultimedia; // ID único del anuncio o publicación
    private String titulo; // Título del anuncio (ej: "Nueva colección de libros 2025")
    private String descripcion; // Descripción detallada del anuncio o noticia
    private String urlImagen; // URL de imagen principal relacionada (opcional)
    private String urlVideo; // URL de video relacionado (opcional)
    private Date fechaPublicacion; // Fecha en la que se publica el anuncio
    private Date fechaExpiracion; // Fecha en la que deja de mostrarse (opcional)
    private String tipoPublicacion; // Tipo de publicación (evento, promoción, aviso general)
}
