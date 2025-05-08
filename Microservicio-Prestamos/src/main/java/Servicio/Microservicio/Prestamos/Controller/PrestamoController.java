package Servicio.Microservicio.Prestamos.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Servicio.Microservicio.Prestamos.Model.Prestamo;
import Servicio.Microservicio.Prestamos.Service.PrestamoService;

@RestController
@RequestMapping("/api/v1/prestamos")
public class PrestamoController {
    @Autowired
    private PrestamoService prestamoService;

    // crear un nuevo prestamo
    @PostMapping
    public ResponseEntity<Prestamo> crearPrestamo(@RequestBody Prestamo prestamo) {
        Prestamo nuevoPrestamo = prestamoService.crearPrestamo(prestamo);
        return ResponseEntity.status(201).body(nuevoPrestamo);
    }

    // obtener todos los prestamos
    @GetMapping
    public ResponseEntity<List<Prestamo>> obtenerPrestamos() {
        List<Prestamo> prestamos = prestamoService.obtenerTodosLosPrestamos();
        return ResponseEntity.ok(prestamos);
    }

    // obtener prestamos por id
    @GetMapping("/{id}")
    public ResponseEntity<Prestamo> obtenerPrestamoPorId(@PathVariable Integer id) {
        Optional<Prestamo> prestamoOpt = Optional.ofNullable(prestamoService.obtenerPrestamoPorId(id));
        return prestamoOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // obtener prestamos por run
    @GetMapping("/run/{run}")
    public ResponseEntity<List<Prestamo>> obtenerPrestamosPorRun(@PathVariable String run) {
        List<Prestamo> prestamos = prestamoService.obtenerPrestamosPorRun(run);
        if (prestamos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(prestamos);
        }
    }

    // obtener prestamos pendientes (sin fecha de entrega)
    @GetMapping("/pendientes")
    public ResponseEntity<List<Prestamo>> obtenerPrestamosPendientes() {
        List<Prestamo> pendientes = prestamoService.obtenerPrestamoPendientes();
        if (pendientes.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(pendientes);
        }
    }

    // actualizar un prestamo
    @PutMapping("/{id}")
    public ResponseEntity<Prestamo> actualizarPrestamo(@PathVariable Integer id, @RequestBody Prestamo prestamo){
        if(prestamoService.obtenerPrestamoPorId(id)==null ){
            return ResponseEntity.notFound().build();
        } 
        prestamo.setIdPrestamo(id);
        Prestamo prestamoActualizado = prestamoService.actualizarPrestamo(prestamo);
        return ResponseEntity.ok(prestamoActualizado);
    }

    // Eliminar un préstamo
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminarPrestamo(@PathVariable Integer id) {
        if (prestamoService.obtenerPrestamoPorId(id) == null) {
            return ResponseEntity.notFound().build();
        }
        prestamoService.eliminarPrestamo(id);
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Prestamo eliminado con exito");
        return ResponseEntity.ok(response);
    }
}
