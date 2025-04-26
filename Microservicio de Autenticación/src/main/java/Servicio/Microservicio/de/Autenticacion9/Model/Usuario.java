package Servicio.Microservicio.de.Autenticacion9.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Usuario {
    private int idUsuario; // ID único del usuario
    private String nombre; // Nombre completo del usuario
    private String correo; // Correo electrónico para iniciar sesión
    private String contrasena; // Contraseña (en la vida real, debería estar encriptada)
}
