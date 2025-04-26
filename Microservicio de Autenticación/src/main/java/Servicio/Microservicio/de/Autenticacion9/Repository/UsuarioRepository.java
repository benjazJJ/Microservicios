package Servicio.Microservicio.de.Autenticacion9.Repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import Servicio.Microservicio.de.Autenticacion9.Model.Usuario;

@Repository
public class UsuarioRepository {

    private List<Usuario> listaUsuarios = new ArrayList<>();

    public UsuarioRepository() {
        listaUsuarios.add(new Usuario(1, "Carlos Muñoz", "carlos.munoz@example.com", "contrasena123"));
        listaUsuarios.add(new Usuario(2, "María López", "maria.lopez@example.com", "pass456"));
    }

    public List<Usuario> obtenerUsuarios() {
        return new ArrayList<>(listaUsuarios);
    }

    // Busca un usuario por su ID
    public Usuario buscarPorId(int idUsuario) {
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getIdUsuario() == idUsuario) {
                return usuario;
            }
        }
        return null;
    }

    // Guarda un nuevo usuario, validando que no exista un ID duplicado
    public Usuario guardar(Usuario usuario) {
        if (buscarPorId(usuario.getIdUsuario()) != null) {
            throw new IllegalArgumentException("Ya existe un usuario con el ID " + usuario.getIdUsuario());
        }
        listaUsuarios.add(usuario);
        return usuario;
    }

    // Actualiza un usuario existente, validando que el ID exista primero
    public Usuario actualizar(Usuario usuario) {
        Usuario existenteusuario = buscarPorId(usuario.getIdUsuario());
        if (existenteusuario == null) {
            throw new IllegalArgumentException("No se puede actualizar porque no existe un usuario con ID " + usuario.getIdUsuario());
        }
        eliminar(usuario.getIdUsuario());
        listaUsuarios.add(usuario);
        return usuario;
    }

    // Elimina un usuario por su ID, validando que exista
    public void eliminar(int idUsuario) {
        Usuario usuarioEncontrado = buscarPorId(idUsuario);
        if (usuarioEncontrado == null) {
            throw new IllegalArgumentException("No se puede eliminar porque no existe un usuario con ID " + idUsuario);
        }
        listaUsuarios.remove(usuarioEncontrado);
    }
}
