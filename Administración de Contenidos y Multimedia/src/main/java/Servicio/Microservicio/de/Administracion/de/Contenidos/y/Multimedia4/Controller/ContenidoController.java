package Servicio.Microservicio.de.Administracion.de.Contenidos.y.Multimedia4.Controller;

import Servicio.Microservicio.de.Administracion.de.Contenidos.y.Multimedia4.Model.Contenido;
import Servicio.Microservicio.de.Administracion.de.Contenidos.y.Multimedia4.Service.ContenidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contenidos")
public class ContenidoController {

    @Autowired
    private ContenidoService contenidoService;

    @GetMapping
    public ResponseEntity<List<Contenido>> listarContenidos() {
        List<Contenido> contenidos = contenidoService.getContenidos();
        if (contenidos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(contenidos);
    }

    @PostMapping
    public ResponseEntity<Contenido> agregarContenido(@RequestBody Contenido contenido) {
        Contenido nuevo = contenidoService.saveContenido(contenido);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contenido> buscarContenido(@PathVariable int id) {
        Contenido contenido = contenidoService.getContenidoId(id);
        if (contenido != null) {
            return ResponseEntity.ok(contenido);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contenido> actualizarContenido(@PathVariable int id, @RequestBody Contenido contenido) {
        contenido.setIdContenido(id);
        Contenido actualizado = contenidoService.updateContenido(contenido);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarContenido(@PathVariable int id) {
        String mensaje = contenidoService.deleteContenido(id);
        if (mensaje.contains("correctamente")) {
            return ResponseEntity.ok(mensaje);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
        }
    }
}

