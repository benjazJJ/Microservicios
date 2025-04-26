package Servicio.Microservicio.de.Autenticacion9.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Login {
    private int idLogin;            //ID Login del Usuario
    private String correo;          // Correo usado para iniciar sesión
    private String contrasena;      // Contraseña del usuario
}
