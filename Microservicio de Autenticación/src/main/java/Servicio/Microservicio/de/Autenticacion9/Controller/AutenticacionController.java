package Servicio.Microservicio.de.Autenticacion9.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Servicio.Microservicio.de.Autenticacion9.Model.Autenticacion;
import Servicio.Microservicio.de.Autenticacion9.Service.AutenticacionService;

@RestController
@RequestMapping("/api/v1/autenticacion")
public class AutenticacionController {

    @Autowired
    private AutenticacionService loginService;

    // Obtener todos los usuarios
    @GetMapping
    public List<Autenticacion> listarLogins() {
        return loginService.getLogins();
    }

    // Agregar un nuevo contenido
    @PostMapping
    public Autenticacion agregarUsuario(@RequestBody Autenticacion login){
        return loginService.saveLogin(login);
    }

    // Buscar un login por ID
    @GetMapping("/{id}")
    public Autenticacion buscarLogin(@PathVariable int id) {
        return loginService.getLoginID(id);
    }

    // Actualizar un Usuario
    @PutMapping("/{id}")
    public Autenticacion actualizarLogin(@PathVariable int id, @RequestBody Autenticacion login) {
        login.setIdLogin(id);
        return loginService.updateLogin(login);
    }

    // Eliminar un contenido
    @DeleteMapping("/{id}")
    public String eliminarLogin(@PathVariable int id) {
        return loginService.deleteLogin(id);
    }

}
