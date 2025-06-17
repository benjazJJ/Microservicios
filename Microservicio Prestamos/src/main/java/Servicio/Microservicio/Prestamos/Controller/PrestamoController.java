// PrestamoController.java
package Servicio.Microservicio.Prestamos.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import Servicio.Microservicio.Prestamos.Model.Prestamo;
import Servicio.Microservicio.Prestamos.Service.PrestamoService;
import Servicio.Microservicio.Prestamos.Service.ValidacionResponse;

@RestController
@RequestMapping("/api/v1/prestamos")
public class PrestamoController {

    @Autowired
    private PrestamoService prestamoService;

    // crear un nuevo prestamo validando correo, contraseña y rol
    @PostMapping
    public ResponseEntity<?> crearPrestamo(@RequestBody Map<String, Object> datos) {
        try {
            String correo = (String) datos.get("correo");
            String contrasena = (String) datos.get("contrasena");

            Prestamo prestamo = prestamoService.crearPrestamoSiEsValido(datos, correo, contrasena);
            return ResponseEntity.status(201).body(prestamo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Prestamo>> obtenerPrestamos() {
        List<Prestamo> prestamos = prestamoService.obtenerTodosLosPrestamos();
        return ResponseEntity.ok(prestamos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prestamo> obtenerPrestamoPorId(@PathVariable Integer id) {
        Optional<Prestamo> prestamoOpt = Optional.ofNullable(prestamoService.obtenerPrestamoPorId(id));
        return prestamoOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/run/{run}")
    public ResponseEntity<List<Prestamo>> obtenerPrestamosPorRun(@PathVariable String run) {
        List<Prestamo> prestamos = prestamoService.obtenerPrestamosPorRun(run);
        if (prestamos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(prestamos);
        }
    }

    @GetMapping("/pendientes")
    public ResponseEntity<List<Prestamo>> obtenerPrestamosPendientes() {
        List<Prestamo> pendientes = prestamoService.obtenerPrestamoPendientes();
        if (pendientes.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(pendientes);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prestamo> actualizarPrestamo(@PathVariable Integer id, @RequestBody Prestamo prestamo) {
        if (prestamoService.obtenerPrestamoPorId(id) == null) {
            return ResponseEntity.notFound().build();
        }
        prestamo.setIdPrestamo(id);
        Prestamo prestamoActualizado = prestamoService.actualizarPrestamo(prestamo);
        return ResponseEntity.ok(prestamoActualizado);
    }

    @PostMapping("/eliminar/{id}")
    public ResponseEntity<Map<String, String>> eliminarPrestamo(
            @PathVariable Integer id,
            @RequestBody Map<String, String> datos) {

        String correo = datos.get("correo");
        String contrasena = datos.get("contrasena");

        WebClient client = WebClient.builder()
                .baseUrl("http://localhost:8081")
                .build();

        ValidacionResponse validacion = client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v1/autenticacion/validar")
                        .queryParam("correo", correo)
                        .queryParam("contrasena", contrasena)
                        .build())
                .retrieve()
                .bodyToMono(ValidacionResponse.class)
                .block();

        if (validacion == null || !validacion.autenticado || !validacion.rol.equalsIgnoreCase("ADMINISTRADOR")) {
            return ResponseEntity.status(403)
                    .body(Map.of("error", "Acceso denegado. Solo un administrador puede eliminar préstamos."));
        }

        if (prestamoService.obtenerPrestamoPorId(id) == null) {
            return ResponseEntity.notFound().build();
        }

        prestamoService.eliminarPrestamo(id);
        return ResponseEntity.ok(Map.of("mensaje", "Préstamo eliminado con éxito"));
    }

}
