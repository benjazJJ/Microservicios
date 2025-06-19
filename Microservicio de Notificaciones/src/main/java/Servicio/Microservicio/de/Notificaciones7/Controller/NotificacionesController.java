package Servicio.Microservicio.de.Notificaciones7.Controller;

import Servicio.Microservicio.de.Notificaciones7.Model.Notificaciones;
import Servicio.Microservicio.de.Notificaciones7.Service.NotificacionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/notificaciones")
public class NotificacionesController {

    @Autowired
    private NotificacionesService notificacionesService;

    // GET: Obtener todas las notificaciones
    @GetMapping
    public List<Notificaciones> obtenerTodas() {
        return notificacionesService.obtenerTodas();
    }

    // GET por ID
    @GetMapping("/{id}")
    public ResponseEntity<Notificaciones> obtenerPorId(@PathVariable int id) {
        return notificacionesService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET por emisor
    @GetMapping("/emisor/{correo}")
    public List<Notificaciones> obtenerPorEmisor(@PathVariable String correo) {
        return notificacionesService.obtenerPorEmisor(correo);
    }

    // GET por receptor
    @GetMapping("/receptor/{correo}")
    public List<Notificaciones> obtenerPorReceptor(@PathVariable String correo) {
        return notificacionesService.obtenerPorReceptor(correo);
    }

    // POST: Crear notificación (ADMINISTRADOR o BIBLIOTECARIO)
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Map<String, Object> body) {
        try {
            Notificaciones noti = new Notificaciones();
            noti.setMensaje((String) body.get("mensaje"));
            noti.setTipo((String) body.get("tipo"));
            noti.setCorreoEmisor((String) body.get("correoEmisor"));
            noti.setCorreoReceptor((String) body.get("correoReceptor"));

            String correo = (String) body.get("correo");
            String contrasena = (String) body.get("contrasena");

            Notificaciones creada = notificacionesService.crear(noti, correo, contrasena);
            return ResponseEntity.status(201).body(creada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        }
    }
    
    // DELETE: Eliminar notificación (solo ADMINISTRADOR o BIBLIOTECARIO)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id, @RequestBody Map<String, String> body) {
        try {
            String correo = body.get("correo");
            String contrasena = body.get("contrasena");

            notificacionesService.eliminar(id, correo, contrasena);
            return ResponseEntity.ok("Notificación eliminada correctamente.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        }
    }
}
