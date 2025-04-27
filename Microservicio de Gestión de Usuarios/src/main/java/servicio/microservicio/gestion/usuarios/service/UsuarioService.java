package servicio.microservicio.gestion.usuarios.service;

import servicio.microservicio.gestion.usuarios.model.Usuario;
import servicio.microservicio.gestion.usuarios.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Obtener todos los usuarios
    public List<Usuario> getUsuarios() {
        return usuarioRepository.obtenerUsuarios();
    }

    // Guardar un nuevo usuario
    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.guardar(usuario);
    }

    // Buscar un usuario por su ID
    public Usuario getUsuarioById(int idUsuario) {
        return usuarioRepository.buscarPorId(idUsuario);
    }

    // Actualizar un usuario existente
    public Usuario updateUsuario(Usuario usuario) {
        return usuarioRepository.actualizar(usuario);
    }

    // Eliminar un usuario por su ID
    public String deleteUsuario(int idUsuario) {
        usuarioRepository.eliminar(idUsuario);
        return "Usuario eliminado correctamente";
    }
}
