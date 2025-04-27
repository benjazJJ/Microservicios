package servicio.microservicio.gestion.usuarios.repository;

import servicio.microservicio.gestion.usuarios.model.Usuario;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioRepository {

    private final List<Usuario> listaUsuarios = new ArrayList<>();

    // Constructor: agregamos usuarios de ejemplo 
    public UsuarioRepository() {
        listaUsuarios.add(new Usuario(1, "Benja Palma", "20.123.456-7", "benja@example.com", "+56912345678"));
        listaUsuarios.add(new Usuario(2, "Carlos Muñoz", "19.876.543-2", "carlos@example.com", "+56987654321"));
    }

    // Devuelve todos los usuarios
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
        Usuario existente = buscarPorId(usuario.getIdUsuario());
        if (existente == null) {
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
