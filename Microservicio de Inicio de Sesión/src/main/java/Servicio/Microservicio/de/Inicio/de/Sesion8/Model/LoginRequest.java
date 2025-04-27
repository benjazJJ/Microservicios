package Servicio.Microservicio.de.Inicio.de.Sesion8.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    private String correo;       // Correo ingresado en el formulario de login
    private String contrasena;   // Contraseña ingresada en el formulario de login
}
// Esta clase representa la solicitud de inicio de sesión que contiene el correo y la contraseña ingresados por el usuario.