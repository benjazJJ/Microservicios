package Servicio.AutenticacionCuenta.controller;

import Servicio.AutenticacionCuenta.model.Usuario;
import Servicio.AutenticacionCuenta.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/autenticacion")
public class AutenticacionController {

    // Inyectamos el servicio que contiene la lógica de autenticación
    @Autowired
    private UsuarioService usuarioService;

    // Endpoint de login activo
    // Este método recibe un objeto Usuario en formato JSON con correo y contraseña
    // y verifica si las credenciales son válidas.
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Usuario usuario) {
        boolean autenticado = usuarioService.autenticar(usuario.getCorreo(), usuario.getContrasena());
        return autenticado
                ? ResponseEntity.ok("Autenticación exitosa")
                : ResponseEntity.status(401).body("Credenciales inválidas");
    }

    // Endpoint de registro desactivado por ahora.
    // Lo dejo comentado para su futuro uso si es que llegamos a usarlo @Benjaleal y @gustavo

    // @PostMapping("/registro")
    // public ResponseEntity<Usuario> registrar(@RequestBody Usuario usuario) {
    //     Usuario nuevo = usuarioService.registrar(usuario);
    //     return ResponseEntity.ok(nuevo);
    // }
}
