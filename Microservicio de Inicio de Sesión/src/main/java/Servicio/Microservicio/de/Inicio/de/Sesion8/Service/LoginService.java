package Servicio.Microservicio.de.Inicio.de.Sesion8.Service;

import Servicio.Microservicio.de.Inicio.de.Sesion8.Repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    // Valida las credenciales de inicio de sesión
    public boolean validarCredenciales(String correo, String contrasena) {
        return loginRepository.validarLogin(correo, contrasena);
    }
}

