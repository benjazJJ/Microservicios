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

import Servicio.Microservicio.de.Autenticacion9.Model.Usuario;
import Servicio.Microservicio.de.Autenticacion9.Service.UsuarioService;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Obtener todos los usuarios
    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioService.getUsuarios();
    }

    // Agregar un nuevo contenido
    @PostMapping
    public Usuario agregarUsuario(@RequestBody Usuario usuario){
        return usuarioService.saveUsuario(usuario);
    }

    // Buscar un Usuario por ID
    @GetMapping("{id}")
    public Usuario buscarUsuario(@PathVariable int id){
        return usuarioService.getUsuarioId(id);
    }

    // Actualizar un Usuario
    @PutMapping("/{id}")
    public Usuario actualizarUsuario(@PathVariable int id, @RequestBody Usuario usuario) {
        usuario.setIdUsuario(id);
        return usuarioService.updateUsuario(usuario);
    }

    // Eliminar un contenido
    @DeleteMapping("/{id}")
    public String eliminarUsuario(@PathVariable int id) {
        return usuarioService.deleteUsuario(id);
    }

}
