package Servicio.Microservicio.de.Administracion.de.Contenidos.y.Multimedia4.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity // Indica que esta clase es una entidad JPA
@Table(name = "contenido") // Nombre de la tabla en la base de datos
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contenido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID autoincremental en MySQL
    @Column(name = "id_contenido")
    private int idContenido;

    @Column(name = "nombre_archivo", nullable = false, length = 100)
    private String nombreArchivo; // Nombre del archivo subido

    @Column(name = "tipo_archivo", nullable = false, length = 20)
    private String tipoArchivo; // Tipo del archivo (pdf, jpg, etc.)

    @Column(name = "url_archivo", nullable = false, length = 255)
    private String urlArchivo; // Ruta o URL donde está almacenado

    @Column(name = "fecha_subida", nullable = false)
    private Date fechaSubida; // Fecha de subida

    @Column(name = "descripcion", length = 255)
    private String descripcion; // Descripción del contenido

    @Column(name = "visibilidad", length = 50)
    private String visibilidad; // Nivel de visibilidad (público, privado, etc.)
}


