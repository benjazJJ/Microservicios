package Servicio.Microservicio.de.Devolucion6.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Devolucion {
    private int idDevolucion;         // ID único de la devolución
    private int idUsuario;            // ID del usuario que devuelve el libro
    private int idLibro;              // ID del libro devuelto
    private Date fechaDevolucion;     // Fecha en que se devolvió el libro
    private String estadoLibro;       // Estado del libro al ser devuelto (Ej: Bueno, Dañado, Perdido)
    private String observaciones;     // Comentarios adicionales sobre la devolución
}