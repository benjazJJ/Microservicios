package com.example.Microservicio.Multas.Controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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

import com.example.Microservicio.Multas.Model.Multa;
import com.example.Microservicio.Multas.Service.MultaService;

@RestController
@RequestMapping("/api/multas")
public class MultaController {
    @Autowired
    private MultaService multaService;

    @GetMapping
    public List<Multa> listarTodas() {
        return multaService.obtenerTodasLasMultas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Multa> obtenerPorId(@PathVariable Long id) {
        return multaService.obtenerMultaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Multa> crear(@RequestBody Multa multa) {
        return ResponseEntity.ok(multaService.crearMulta(multa));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Multa> actualizar(@PathVariable Long id, @RequestBody Multa multa) {
        return multaService.actualizarMulta(id, multa)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        return multaService.eliminarMulta(id)
                ? ResponseEntity.ok("Multa eliminada correctamente")
                : ResponseEntity.notFound().build();
    }

    // Endpoint adicional para asignar multas automáticamente
    @PostMapping("/asignar")
    public ResponseEntity<?> asignarMulta(@RequestBody Map<String, String> datos) {
        try {
            String runUsuario = datos.get("runUsuario");
            LocalDate fechaEsperada = LocalDate.parse(datos.get("fechaEsperada"));
            LocalDate fechaReal = LocalDate.parse(datos.get("fechaReal"));

            Multa multa = multaService.asignarMultaAutomatica(runUsuario, fechaEsperada, fechaReal);
            return ResponseEntity.ok(multa);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
