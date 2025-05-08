package Microservicio.de.Control.de.roles.y.Permisos.Microservicio.de.roles.y.permisos.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Microservicio.de.Control.de.roles.y.Permisos.Microservicio.de.roles.y.permisos.Model.Permisos;
import Microservicio.de.Control.de.roles.y.Permisos.Microservicio.de.roles.y.permisos.Model.Rol;
import Microservicio.de.Control.de.roles.y.Permisos.Microservicio.de.roles.y.permisos.Service.PermisoService;
import Microservicio.de.Control.de.roles.y.Permisos.Microservicio.de.roles.y.permisos.Service.RolService;

@RestController
@RequestMapping("/api/permisos")

public class PermisoController {
    @Autowired
    private PermisoService permisoService;

    @Autowired
    private RolService rolService;

    // Crear permiso
    @PostMapping
    public ResponseEntity<Map<String, Object>> crearPermiso(@RequestBody Permisos permiso) {
        Map<String, Object> response = new HashMap<>();

        if (permiso.getRol() == null || permiso.getRol().getId() == null) {
            response.put("mensaje", "El permiso debe tener un rol con ID válido.");
            return ResponseEntity.badRequest().body(response);
        }

        Optional<Rol> rolOpt = rolService.obtenerPorId(permiso.getRol().getId());

        if (rolOpt.isEmpty()) {
            response.put("mensaje", "El rol con ID " + permiso.getRol().getId() + " no existe.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        permiso.setRol(rolOpt.get());
        Permisos nuevoPermiso = permisoService.crearPermiso(permiso);

        response.put("mensaje", "Permiso creado correctamente.");
        response.put("permiso", nuevoPermiso);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Obtener todos los permisos
    @GetMapping
    public ResponseEntity<List<Permisos>> obtenerTodos() {
        return ResponseEntity.ok(permisoService.obtenerPermisos());
    }

    // Obtener permiso por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        Optional<Permisos> permiso = permisoService.obtenerPermisosPorId(id);
        if (permiso.isPresent()) {
            return ResponseEntity.ok(permiso.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Permiso con ID " + id + " no encontrado.");
        }
    }

    // Eliminar permiso por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Permisos> permiso = permisoService.obtenerPermisosPorId(id);
        if (permiso.isPresent()) {
            permisoService.eliminarPermiso(id);
            return ResponseEntity.ok("Permiso eliminado correctamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Permiso con ID " + id + " no encontrado.");
        }
    }

    // Actualizar permiso
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Permisos permisoActualizado) {
        Optional<Permisos> permisoOpt = permisoService.obtenerPermisosPorId(id);

        if (permisoOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Permiso con ID " + id + " no encontrado.");
        }

        Permisos permiso = permisoOpt.get();
        permiso.setNombrePermiso(permisoActualizado.getNombrePermiso());

        if (permisoActualizado.getRol() != null && permisoActualizado.getRol().getId() != null) {
            Optional<Rol> rolOpt = rolService.obtenerPorId(permisoActualizado.getRol().getId());
            if (rolOpt.isPresent()) {
                permiso.setRol(rolOpt.get());
            } else {
                return ResponseEntity.badRequest().body("Rol con ID " + permisoActualizado.getRol().getId() + " no existe.");
            }
        }

        Permisos actualizado = permisoService.crearPermiso(permiso); // Reutiliza método para guardar
        return ResponseEntity.ok(actualizado);
    }


}
