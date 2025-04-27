package Servicio.Microservicio.de.Devolucion6.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Servicio.Microservicio.de.Devolucion6.Model.Devolucion;
import Servicio.Microservicio.de.Devolucion6.Service.DevolucionService;

@RestController
@RequestMapping("/api/v1/devoluciones")
public class DevolucionController {

    @Autowired
    private DevolucionService devolucionService;

    // Obtener todas las devoluciones
    @GetMapping
    public List<Devolucion> listarDevoluciones() {
        return devolucionService.getDevoluciones();
    }

    // Agregar una nueva devolución
    @PostMapping
    public Devolucion agregarDevolucion(@RequestBody Devolucion devolucion) {
        return devolucionService.saveDevolucion(devolucion);
    }

    // Buscar una devolución por ID
    @GetMapping("/{id}")
    public Devolucion buscarDevolucion(@PathVariable int id) {
        return devolucionService.getDevolucionID(id);
    }

    // Actualizar una devolución
    @PutMapping("/{id}")
    public Devolucion actualizarDevolucion(@PathVariable int id, @RequestBody Devolucion devolucion) {
        devolucion.setIdDevolucion(id);
        return devolucionService.updateDevolucion(devolucion);
    }

    // Eliminar una devolución
    @DeleteMapping("/{id}")
    public String eliminarDevolucion(@PathVariable int id) {
        return devolucionService.deleteDevolucion(id);
    }
}
