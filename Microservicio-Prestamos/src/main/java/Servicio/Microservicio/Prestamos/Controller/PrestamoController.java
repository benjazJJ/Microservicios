package Servicio.Microservicio.Prestamos.Controller;

import java.util.List;
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
@RequestMapping("api/v1/prestamos")
public class PrestamoController {
    @Autowired
    private PrestamoService prestamoService;

    //Metodo para crear un nuevo Prestamo
    @PostMapping
    public ResponseEntity<Prestamo> crearPrestamo(@RequestBody Prestamo prestamo){
        Prestamo nuevoPrestamo = prestamoService.crearPrestamo(prestamo);
        return ResponseEntity.ok(nuevoPrestamo);
    }

    //Metodo para obtener los prestamos
    @GetMapping
    public List<Prestamo> obtenerPrestamos(){
        return prestamoService.obtenerTodosLosPrestamos();
    }

    //Metodo para obtener un prestamo por su ID
    @GetMapping("/id")
    public ResponseEntity<Prestamo> obtenerPrestamoPorId(@PathVariable Integer id) {
        Optional<Prestamo> prestamo = Optional.ofNullable(prestamoService.obtenerPrestamoPorId(id));
        return prestamo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Metodo para obtener Prestamos por RUN del solicitante
    //Ejemplo: http://localhost:8085/api/v1/prestamos/run/12345678-9

    @GetMapping("/run/{run}")
    public List<Prestamo> obtenerPrestamosPorRun(@PathVariable String run) {
        return prestamoService.obtenerPrestamosPorRun(run);
    }

    //Metodo obtener préstamos pendientes (sin fecha de entrega)
    @GetMapping("/pendientes")
    public List<Prestamo> obtenerPrestamosPendientes() {
        return prestamoService.obtenerPrestamoPendientes();
    }

    //Metodo para actualizar un préstamo existente
    @PutMapping("/{id}")
    public ResponseEntity<Prestamo> actualizarPrestamo(@PathVariable Integer id, @RequestBody Prestamo prestamo) {
        prestamo.setIdPrestamo(id);
        Prestamo prestamoActualizado = prestamoService.actualizarPrestamo(prestamo);
        return ResponseEntity.ok(prestamoActualizado);
    }

    //Metodo para eliminar un préstamo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPrestamo(@PathVariable Integer id) {
        prestamoService.eliminarPrestamo(id);
        return ResponseEntity.noContent().build();
    }
}
