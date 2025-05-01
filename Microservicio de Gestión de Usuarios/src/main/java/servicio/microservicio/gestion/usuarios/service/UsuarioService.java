package servicio.microservicio.gestion.usuarios.service;

import servicio.microservicio.gestion.usuarios.model.Usuario;
import servicio.microservicio.gestion.usuarios.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Obtener todos los usuarios
    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }

    // Guardar un nuevo usuario
    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Buscar un usuario por ID
    public Usuario getUsuarioById(int idUsuario) {
        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
        return usuario.orElse(null); // Devuelve null si no se encuentra
    }

    // Actualizar usuario (usa save, que actualiza si existe)
    public Usuario updateUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Eliminar usuario por ID
    public String deleteUsuario(int idUsuario) {
        if (usuarioRepository.existsById(idUsuario)) {
            usuarioRepository.deleteById(idUsuario);
            return "Usuario eliminado correctamente";
        } else {
            return "Usuario no encontrado";
        }
    }
}

