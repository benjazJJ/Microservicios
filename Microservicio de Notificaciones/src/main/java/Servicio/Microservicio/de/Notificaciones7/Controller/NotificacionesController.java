package Servicio.Microservicio.de.Notificaciones7.Controller;

import Servicio.Microservicio.de.Notificaciones7.Model.Notificaciones;
import Servicio.Microservicio.de.Notificaciones7.Service.NotificacionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController 
@RequestMapping("/api/v1/notificaciones") 
public class NotificacionesController {

    @Autowired
    private NotificacionesService notificacionesService; 

    // GET: Obtener todas las notificaciones
    @GetMapping
    public List<Notificaciones> obtenerTodas() {
        return notificacionesService.obtenerTodas(); // Devuelve la lista completa de notificaciones
    }

    // GET: Obtener una notificación por ID
    @GetMapping("/{id}")
    public ResponseEntity<Notificaciones> obtenerPorId(@PathVariable int id) {
        // Busca la notificación por ID. Si existe, devuelve 200 OK con el objeto.
        // Si no existe, devuelve 404 Not Found.
        return notificacionesService.obtenerPorId(id)
                .map(ResponseEntity::ok) // Transforma el Optional en un ResponseEntity con status 200
                .orElse(ResponseEntity.notFound().build()); // Si no existe, retorna 404
    }

    // POST: Crear una nueva notificación
    @PostMapping
    public ResponseEntity<Notificaciones> crear(@RequestBody Notificaciones notificacion) {
        Notificaciones creada = notificacionesService.guardar(notificacion); // Guarda la nueva notificación
        return ResponseEntity.ok(creada); // Retorna la notificación guardada con status 200 OK
    }

    // PUT: Actualizar una notificación existente
    @PutMapping("/{id}")
    public ResponseEntity<Notificaciones> actualizar(@PathVariable int id, @RequestBody Notificaciones notificacion) {
        Notificaciones actualizada = notificacionesService.actualizar(id, notificacion); // Actualiza la notificación
        return ResponseEntity.ok(actualizada); // Retorna la notificación actualizada con status 200 OK
    }

    // DELETE: Eliminar una notificación por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        String mensaje = notificacionesService.eliminar(id); // Intenta eliminar la notificación por ID
        return ResponseEntity.ok(mensaje); // Retorna un mensaje indicando si fue eliminado correctamente
    }
}
