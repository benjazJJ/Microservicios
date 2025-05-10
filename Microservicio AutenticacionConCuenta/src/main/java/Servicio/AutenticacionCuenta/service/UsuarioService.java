package Servicio.AutenticacionCuenta.service;

import Servicio.AutenticacionCuenta.model.Usuario;
import Servicio.AutenticacionCuenta.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public boolean autenticar(String correo, String contrasena) {
        return usuarioRepository.findByCorreoAndContrasena(correo, contrasena).isPresent();
    }

    public Usuario registrar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
}
