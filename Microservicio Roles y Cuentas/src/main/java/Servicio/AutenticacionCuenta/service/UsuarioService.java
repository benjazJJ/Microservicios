package Servicio.AutenticacionCuenta.service;

import Servicio.AutenticacionCuenta.model.Usuario;
import Servicio.AutenticacionCuenta.model.Rol;
import Servicio.AutenticacionCuenta.repository.UsuarioRepository;
import Servicio.AutenticacionCuenta.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    // Registra un nuevo usuario como estudiante
    public Usuario registrar(Usuario usuario) {
        if (usuarioRepository.findByCorreo(usuario.getCorreo()).isPresent()) {
            throw new IllegalStateException("Ya existe una cuenta con este correo.");
        }

        if (usuarioRepository.findByRut(usuario.getRut()).isPresent()) {
            throw new IllegalStateException("Ya existe una cuenta con este RUT.");
        }

        // Asignar rol ESTUDIANTE automáticamente
        Rol rolEstudiante = rolRepository.findByNombreRol("ESTUDIANTE")
                .orElseThrow(() -> new RuntimeException("Rol ESTUDIANTE no encontrado."));
        usuario.setRol(rolEstudiante);

        String encriptada = Encriptador.encriptar(usuario.getContrasena());
        usuario.setContrasena(encriptada);

        return usuarioRepository.save(usuario);
    }

    // Autenticar y devolver usuario completo (para /auth/validar)
    public Optional<Usuario> autenticarYObtener(String correo, String contrasena) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByCorreo(correo);
        if (usuarioOpt.isPresent() &&
            Encriptador.comparar(contrasena, usuarioOpt.get().getContrasena())) {
            return usuarioOpt;
        }
        return Optional.empty();
    }

    public Usuario obtenerPorId(int id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario obtenerPorRut(String rut) {
        return usuarioRepository.findByRut(rut).orElse(null);
    }
}
