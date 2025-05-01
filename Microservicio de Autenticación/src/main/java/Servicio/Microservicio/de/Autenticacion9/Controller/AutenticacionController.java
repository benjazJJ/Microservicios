package Servicio.Microservicio.de.Autenticacion9.Controller;

import Servicio.Microservicio.de.Autenticacion9.Model.Autenticacion;
import Servicio.Microservicio.de.Autenticacion9.Service.AutenticacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/autenticacion")
public class AutenticacionController {

    @Autowired
    private AutenticacionService loginService;

    // Obtener todos los usuarios registrados
    @GetMapping
    public List<Autenticacion> listarLogins() {
        return loginService.getLogins();
    }

    // Agregar un nuevo usuario
    @PostMapping
    public Autenticacion agregarUsuario(@RequestBody Autenticacion login) {
        return loginService.saveLogin(login);
    }

    // Validar correo y contraseña (para Inicio de Sesión)
    @PostMapping("/validar")
    public boolean validarLogin(@RequestBody Autenticacion login) {
        return loginService.validarCredenciales(login.getCorreo(), login.getContrasena());
    }
}

