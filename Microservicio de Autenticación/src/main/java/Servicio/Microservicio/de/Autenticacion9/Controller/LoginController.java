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

import Servicio.Microservicio.de.Autenticacion9.Model.Login;
import Servicio.Microservicio.de.Autenticacion9.Service.LoginService;

@RestController
@RequestMapping("/api/v1/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    // Obtener todos los usuarios
    @GetMapping
    public List<Login> listarLogins() {
        return loginService.getLogins();
    }

    // Agregar un nuevo contenido
    @PostMapping
    public Login agregarUsuario(@RequestBody Login login){
        return loginService.saveLogin(login);
    }

    // Buscar un login por ID
    @GetMapping("/{id}")
    public Login buscarLogin(@PathVariable int id) {
        return loginService.getLoginID(id);
    }

    // Actualizar un Usuario
    @PutMapping("/{id}")
    public Login actualizarLogin(@PathVariable int id, @RequestBody Login login) {
        login.setIdLogin(id);
        return loginService.updateLogin(login);
    }

    // Eliminar un contenido
    @DeleteMapping("/{id}")
    public String eliminarLogin(@PathVariable int id) {
        return loginService.deleteLogin(id);
    }

}
