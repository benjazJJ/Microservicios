package Servicio.Microservicio.de.Autenticacion9.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Servicio.Microservicio.de.Autenticacion9.Model.Usuario;
import Servicio.Microservicio.de.Autenticacion9.Repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Devuelve todos los Usuarios
    public List<Usuario> getUsuarios() {
        return usuarioRepository.obtenerUsuarios();
    }

    // Guarda un nuevo usuario
    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.guardar(usuario);
    }

    // Busca un usuario por su ID
    public Usuario getUsuarioId(int idUsuario) {
        return usuarioRepository.buscarPorId(idUsuario);
    }

    // Actualiza un usuario existente
    public Usuario updateUsuario(Usuario usuario) {
        return usuarioRepository.actualizar(usuario);
    }

    // Elimina un usuario por su ID
    public String deleteUsuario(int idUsuario) {
        usuarioRepository.eliminar(idUsuario);
        return "Usuario eliminado correctamente";
    }

}
