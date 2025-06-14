package Microservicio.Microservicio.Roles.Y.Permisos.Controller;

import Microservicio.Microservicio.Roles.Y.Permisos.Model.Usuario;
import Microservicio.Microservicio.Roles.Y.Permisos.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/autenticacion")
public class AutenticacionController {

    @Autowired
    private UsuarioService usuarioService;

    // REGISTRO (asigna automáticamente rol ESTUDIANTE)
    @PostMapping("/registro")
    public ResponseEntity<?> registrar(@RequestBody Usuario usuario) {
        try {
            Usuario nuevo = usuarioService.registrar(usuario);
            return ResponseEntity.ok(nuevo);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno: " + e.getMessage());
        }
    }

    // LOGIN tradicional - solo texto de respuesta
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Usuario usuario) {
        boolean autenticado = usuarioService.autenticar(usuario.getCorreo(), usuario.getContrasena());
        return autenticado
                ? ResponseEntity.ok("Autenticación exitosa")
                : ResponseEntity.status(401).body("Credenciales inválidas");
    }

    // Endpoint para que otros microservicios validen credenciales y reciban datos
    // del usuario
    @GetMapping("/validar")
    public ResponseEntity<?> validar(
            @RequestParam String correo,
            @RequestParam String contrasena) {

        Optional<Usuario> usuarioOpt = usuarioService.autenticarYObtener(correo, contrasena);

        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }

        Usuario usuario = usuarioOpt.get();
        return ResponseEntity.ok().body(new ValidacionResponse(
                true,
                usuario.getId(),
                usuario.getCorreo(),
                usuario.getRol().getNombreRol()));
    }

    // EDITAR datos personales (nombre, rut, telefono, contraseña)
    @PutMapping("/editarcuenta/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable int id, @RequestBody Usuario cambios) {
        Usuario original = usuarioService.obtenerPorId(id);
        if (original == null) {
            return ResponseEntity.status(404).body("Usuario no encontrado");
        }

        // Validar que el que hace la modificación es el mismo dueño (por seguridad)
        if (!original.getCorreo().equals(cambios.getCorreo())) {
            return ResponseEntity.status(403).body("No puedes modificar otra cuenta que no es tuya");
        }

        original.setNombre(cambios.getNombre());
        original.setTelefono(cambios.getTelefono());
        original.setRut(cambios.getRut());

        if (!cambios.getContrasena().isBlank()) {
            String nuevaClave = Microservicio.Microservicio.Roles.Y.Permisos.Service.Encriptador
                    .encriptar(cambios.getContrasena());
            original.setContrasena(nuevaClave);
        }

        return ResponseEntity.ok(usuarioService.registrar(original));
    }

    // ELIMINAR usuario (si es dueño o ADMIN)
    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<?> eliminarUsuario(
            @PathVariable int id,
            @RequestParam String correo,
            @RequestParam String contrasena) {

        Optional<Usuario> solicitanteOpt = usuarioService.autenticarYObtener(correo, contrasena);
        if (solicitanteOpt.isEmpty()) {
            return ResponseEntity.status(401).body("No autorizado");
        }

        Usuario solicitante = solicitanteOpt.get();
        Usuario aEliminar = usuarioService.obtenerPorId(id);
        if (aEliminar == null) {
            return ResponseEntity.status(404).body("Cuenta no encontrada");
        }

        boolean mismoUsuario = solicitante.getId() == id;
        boolean esAdmin = solicitante.getRol().getNombreRol().equalsIgnoreCase("ADMINISTRADOR");

        if (!mismoUsuario && !esAdmin) {
            return ResponseEntity.status(403).body("No puedes eliminar esta cuenta");
        }

        usuarioService.eliminar(id);
        return ResponseEntity.ok("Cuenta eliminada correctamente");
    }

    // CONSULTAR por ID
    @GetMapping("/usuario/{id}")
    public ResponseEntity<?> obtenerUsuarioPorId(@PathVariable int id) {
        Usuario usuario = usuarioService.obtenerPorId(id);
        return usuario != null
                ? ResponseEntity.ok(usuario)
                : ResponseEntity.status(404).body("Usuario no encontrado");
    }

    // CONSULTAR por RUT
    @GetMapping("/usuario/rut/{rut}")
    public ResponseEntity<?> obtenerUsuarioPorRut(@PathVariable String rut) {
        Usuario usuario = usuarioService.obtenerPorRut(rut);
        return usuario != null
                ? ResponseEntity.ok(usuario)
                : ResponseEntity.status(404).body("Usuario no encontrado por RUT");
    }

    // DTO (Data Transfer Object) que se utiliza para devolver información al hacer
    // validación de credenciales
    static class ValidacionResponse {

        // Indica si el usuario fue autenticado correctamente (true/false)
        public boolean autenticado;

        // ID del usuario autenticado
        public int idUsuario;

        // Correo electrónico del usuario autenticado
        public String correo;

        // Nombre del rol asociado al usuario (por ejemplo: ESTUDIANTE, ADMINISTRADOR, BIBLIOTECARIO, DOCENTE)
        public String rol;

        // Constructor que inicializa todos los campos del DTO con los valores
        // proporcionados
        public ValidacionResponse(boolean autenticado, int idUsuario, String correo, String rol) {
            this.autenticado = autenticado;
            this.idUsuario = idUsuario;
            this.correo = correo;
            this.rol = rol;
        }
    }

}
