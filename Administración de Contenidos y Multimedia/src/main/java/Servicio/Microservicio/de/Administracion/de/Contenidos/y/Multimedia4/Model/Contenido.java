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

public class Contenido {
    private int idContenido; // ID único del contenido
    private String nombreArchivo; // Nombre del archivo subido (ej: "Reglamento_Biblioteca.pdf")
    private String tipoArchivo; // Tipo del archivo (pdf, jpg, mp4, etc.)
    private String urlArchivo; // URL o ruta donde está almacenado el archivo (interna o externa)
    private Date fechaSubida; // Fecha en la que el archivo fue subido
    private String descripcion; // Descripción breve del contenido
    private String visibilidad; // Público, restringido, solo docentes, etc.
}

