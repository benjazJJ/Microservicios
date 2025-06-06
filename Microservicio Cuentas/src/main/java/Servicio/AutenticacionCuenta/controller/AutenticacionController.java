package Servicio.AutenticacionCuenta.controller;

import Servicio.AutenticacionCuenta.model.Usuario;
import Servicio.AutenticacionCuenta.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/autenticacion")
public class AutenticacionController {

    @Autowired
    private UsuarioService usuarioService;

    // Endpoint de login activo
    // verifica si las credenciales son válidas.
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Usuario usuario) {
        boolean autenticado = usuarioService.autenticar(usuario.getCorreo(), usuario.getContrasena());
        return autenticado
                ? ResponseEntity.ok("Autenticación exitosa")
                : ResponseEntity.status(401).body("Credenciales inválidas");
    }

    // Endpoint de registro con control de cuentas duplicadas!!!
    @PostMapping("/registro")
    public ResponseEntity<?> registrar(@RequestBody Usuario usuario) {
        try {
            Usuario nuevo = usuarioService.registrar(usuario);
            return ResponseEntity.ok(nuevo);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(409).body(e.getMessage()); // 409 mostramos que hubo conflicto en la creación
                                                                    // de cuenta
        }
    }

    // Consultar usuario por ID
    @GetMapping("/usuario/{id}")
    public ResponseEntity<?> obtenerUsuarioPorId(@PathVariable int id) {
        Usuario usuario = usuarioService.obtenerPorId(id);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.status(404).body("Usuario no encontrado");
        }
    }

    // Consultar usuario por RUT
    @GetMapping("/usuario/rut/{rut}")
    public ResponseEntity<?> obtenerUsuarioPorRut(@PathVariable String rut) {
        Usuario usuario = usuarioService.obtenerPorRut(rut);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.status(404).body("Usuario no encontrado por RUT");
        }
    }

}
