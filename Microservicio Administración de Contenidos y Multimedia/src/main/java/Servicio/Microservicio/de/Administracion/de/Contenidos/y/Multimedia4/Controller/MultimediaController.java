package Servicio.Microservicio.de.Administracion.de.Contenidos.y.Multimedia4.Controller;

import Servicio.Microservicio.de.Administracion.de.Contenidos.y.Multimedia4.Model.Multimedia;
import Servicio.Microservicio.de.Administracion.de.Contenidos.y.Multimedia4.Service.MultimediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/multimedias")
public class MultimediaController {

    @Autowired
    private MultimediaService multimediaService;

    @GetMapping
    public ResponseEntity<List<Multimedia>> listarMultimedias() {
        List<Multimedia> lista = multimediaService.getMultimedias();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<Multimedia> agregarMultimedia(@RequestBody Multimedia multimedia) {
        Multimedia nuevo = multimediaService.saveMultimedia(multimedia);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Multimedia> buscarMultimedia(@PathVariable int id) {
        Multimedia encontrado = multimediaService.getMultimediaId(id);
        if (encontrado != null) {
            return ResponseEntity.ok(encontrado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Multimedia> actualizarMultimedia(@PathVariable int id, @RequestBody Multimedia multimedia) {
        multimedia.setIdMultimedia(id);
        Multimedia actualizado = multimediaService.updateMultimedia(multimedia);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarMultimedia(@PathVariable int id) {
        String mensaje = multimediaService.deleteMultimedia(id);
        if (mensaje.contains("correctamente")) {
            return ResponseEntity.ok(mensaje);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
        }
    }
}
