package servicio.microservicio.gestion.usuarios.controller;

import servicio.microservicio.gestion.usuarios.model.Usuario;
import servicio.microservicio.gestion.usuarios.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Obtener todos los usuarios
    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = usuarioService.getUsuarios();
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    // Obtener un usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuario(@PathVariable int id) {
        Usuario usuario = usuarioService.getUsuarioById(id);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Agregar un nuevo usuario
    @PostMapping
    public ResponseEntity<Usuario> agregarUsuario(@RequestBody Usuario usuario) {
        Usuario nuevo = usuarioService.saveUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    // Actualizar un usuario existente
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable int id, @RequestBody Usuario usuario) {
        usuario.setIdUsuario(id);
        Usuario actualizado = usuarioService.updateUsuario(usuario);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar un usuario por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable int id) {
        String mensaje = usuarioService.deleteUsuario(id);
        if (mensaje.contains("correctamente")) {
            return ResponseEntity.ok(mensaje);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
        }
    }
}
