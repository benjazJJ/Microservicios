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

    // POST que guarda las credenciales enviadas desde Inicio de Sesión
    @PostMapping
    public Autenticacion recibirLogin(@RequestBody Autenticacion login) {
        return loginService.saveLogin(login);
    }

    // GET para ver lo que ya se guardó
    @GetMapping
    public List<Autenticacion> listarLogins() {
        return loginService.getLogins();
    }
}
