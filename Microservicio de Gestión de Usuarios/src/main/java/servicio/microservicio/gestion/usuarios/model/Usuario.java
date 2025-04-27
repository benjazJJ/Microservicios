package servicio.microservicio.gestion.usuarios.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    private int idUsuario;      // ID único del usuario
    private String nombre;      // Nombre completo del usuario
    private String rut;        // RUT del usuario
    private String correo;      // Correo electrónico del usuario
    private String telefono;    // Número de teléfono
}
