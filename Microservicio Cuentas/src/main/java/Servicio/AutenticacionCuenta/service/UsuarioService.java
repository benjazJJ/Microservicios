package Servicio.AutenticacionCuenta.service;

import Servicio.AutenticacionCuenta.model.Usuario;
import Servicio.AutenticacionCuenta.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Registra un nuevo usuario si el correo no está repetido
    public Usuario registrar(Usuario usuario) {
        if (usuarioRepository.findByCorreo(usuario.getCorreo()).isPresent()) {
            throw new IllegalStateException("Ya existe una cuenta con este correo.");
        }

        if (usuarioRepository.findByRut(usuario.getRut()).isPresent()) {
            throw new IllegalStateException("Ya existe una cuenta con este RUT.");
        }

        String encriptada = Encriptador.encriptar(usuario.getContrasena());
        usuario.setContrasena(encriptada);
        return usuarioRepository.save(usuario);
    }

    // Verifica si las credenciales son correctas
    public boolean autenticar(String correo, String contrasena) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByCorreo(correo);
        return usuarioOpt.isPresent() &&
                Encriptador.comparar(contrasena, usuarioOpt.get().getContrasena());
    }

    public Usuario obtenerPorId(int id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario obtenerPorRut(String rut) {
        return usuarioRepository.findByRut(rut).orElse(null);
    }

}
