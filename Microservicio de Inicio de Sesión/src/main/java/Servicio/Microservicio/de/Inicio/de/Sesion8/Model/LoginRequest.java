package Servicio.Microservicio.de.Inicio.de.Sesion8.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    private String correo;
    private String contrasena;
}
