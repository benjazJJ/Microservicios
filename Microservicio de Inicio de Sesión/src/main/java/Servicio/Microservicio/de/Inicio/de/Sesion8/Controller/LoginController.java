package Servicio.Microservicio.de.Inicio.de.Sesion8.Controller;

import Servicio.Microservicio.de.Inicio.de.Sesion8.Model.LoginRequest;
import Servicio.Microservicio.de.Inicio.de.Sesion8.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    // Endpoint para validar login
    @PostMapping
    public String login(@RequestBody LoginRequest loginRequest) {
        boolean credencialesValidas = loginService.validarCredenciales(loginRequest.getCorreo(), loginRequest.getContrasena());

        if (credencialesValidas) {
            return "Inicio de sesión exitoso";
        } else {
            return "Credenciales incorrectas";
        }
    }
}
