package Servicio.Microservicio.de.Administracion.de.Contenidos.y.Multimedia4.Controller;

import Servicio.Microservicio.de.Administracion.de.Contenidos.y.Multimedia4.Model.Multimedia;
import Servicio.Microservicio.de.Administracion.de.Contenidos.y.Multimedia4.Service.MultimediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/multimedias")
public class MultimediaController {

    @Autowired
    private MultimediaService multimediaService;

    // Obtener todas las publicaciones multimedia
    @GetMapping
    public List<Multimedia> listarMultimedias() {
        return multimediaService.getMultimedias();
    }

    // Agregar una nueva publicación multimedia
    @PostMapping
    public Multimedia agregarMultimedia(@RequestBody Multimedia multimedia) {
        return multimediaService.saveMultimedia(multimedia);
    }

    // Buscar una publicación por ID
    @GetMapping("/{id}")
    public Multimedia buscarMultimedia(@PathVariable int id) {
        return multimediaService.getMultimediaId(id);
    }

    // Actualizar una publicación
    @PutMapping("/{id}")
    public Multimedia actualizarMultimedia(@PathVariable int id, @RequestBody Multimedia multimedia) {
        multimedia.setIdMultimedia(id);
        return multimediaService.updateMultimedia(multimedia);
    }

    // Eliminar una publicación
    @DeleteMapping("/{id}")
    public String eliminarMultimedia(@PathVariable int id) {
        return multimediaService.deleteMultimedia(id);
    }
}
