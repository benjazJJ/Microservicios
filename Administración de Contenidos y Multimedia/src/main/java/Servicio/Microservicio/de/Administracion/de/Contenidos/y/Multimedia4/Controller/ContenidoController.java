package Servicio.Microservicio.de.Administracion.de.Contenidos.y.Multimedia4.Controller;

import Servicio.Microservicio.de.Administracion.de.Contenidos.y.Multimedia4.Model.Contenido;
import Servicio.Microservicio.de.Administracion.de.Contenidos.y.Multimedia4.Service.ContenidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contenidos")
public class ContenidoController {

    @Autowired
    private ContenidoService contenidoService;

    // Obtener todos los contenidos
    @GetMapping
    public List<Contenido> listarContenidos() {
        return contenidoService.getContenidos();
    }

    // Agregar un nuevo contenido
    @PostMapping
    public Contenido agregarContenido(@RequestBody Contenido contenido) {
        return contenidoService.saveContenido(contenido);
    }

    // Buscar un contenido por ID
    @GetMapping("/{id}")
    public Contenido buscarContenido(@PathVariable int id) {
        return contenidoService.getContenidoId(id);
    }

    // Actualizar un contenido
    @PutMapping("/{id}")
    public Contenido actualizarContenido(@PathVariable int id, @RequestBody Contenido contenido) {
        contenido.setIdContenido(id);
        return contenidoService.updateContenido(contenido);
    }

    // Eliminar un contenido
    @DeleteMapping("/{id}")
    public String eliminarContenido(@PathVariable int id) {
        return contenidoService.deleteContenido(id);
    }
}
