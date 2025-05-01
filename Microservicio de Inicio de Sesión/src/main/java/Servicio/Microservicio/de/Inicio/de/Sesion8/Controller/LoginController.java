package Servicio.Microservicio.de.Inicio.de.Sesion8.Controller;

import Servicio.Microservicio.de.Inicio.de.Sesion8.Model.LoginRequest;
import Servicio.Microservicio.de.Inicio.de.Sesion8.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    // Endpoint para validar login
    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        boolean credencialesValidas = loginService.validarCredenciales(
            loginRequest.getCorreo(), 
            loginRequest.getContrasena()
        );

        if (credencialesValidas) {
            return ResponseEntity.ok("Inicio de sesión exitoso");
        } else {
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }
    }
}
