package Servicio.Microservicio.de.Inicio.de.Sesion8.Repository;

import Servicio.Microservicio.de.Inicio.de.Sesion8.Model.LoginRequest;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LoginRepository {

    private List<LoginRequest> listaLogins = new ArrayList<>();

    public LoginRepository() {
        // Cargando datos de ejemplo
        listaLogins.add(new LoginRequest("carlos.munoz@example.com", "contrasena123"));
        listaLogins.add(new LoginRequest("maria.lopez@example.com", "pass456"));
    }

    // Devuelve todos los logins registrados
    public List<LoginRequest> obtenerLogins() {
        return new ArrayList<>(listaLogins);
    }

    // Buscar un login por correo y contraseña
    public boolean validarLogin(String correo, String contrasena) {
        for (LoginRequest login : listaLogins) {
            if (login.getCorreo().equals(correo) && login.getContrasena().equals(contrasena)) {
                return true; // Login exitoso
            }
        }
        return false; // Credenciales incorrectas
    }
}
