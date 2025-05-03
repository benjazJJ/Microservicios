package Servicio.Donaciones.Controller;

import org.springframework.web.bind.annotation.RestController;

import Servicio.Donaciones.Model.Donaciones;
import Servicio.Donaciones.Service.DonacionService;

import java.util.List;

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

@RestController
@RequestMapping("/api/v1/donaciones")
public class DonacionesController {

    @Autowired
    private DonacionService donacionService;

    // metodo para obtener todas las donaciones
    @GetMapping
    public ResponseEntity<List<Donaciones>> listar() {
        // guardar en una lista nueva los elementos
        List<Donaciones> donaciones = donacionService.listarDonaciones();
        if (donaciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(donaciones);
    }

    // Metodo para guardar una donacion
    @PostMapping
    public ResponseEntity<Donaciones> guardarDonaciones(@RequestBody Donaciones donaciones) {
        // creamos un objeto nuevo
        Donaciones don = donacionService.guardarDonacion(donaciones);
        return ResponseEntity.status(HttpStatus.CREATED).body(don);
    }

    // Metodo para buscar Donacion por ID
    @GetMapping("/{id}")
    public ResponseEntity<Donaciones> buscarDonacionesPorId(@PathVariable Integer id) {
        try {
            // Verificar si el Donaciones existe
            Donaciones pac = donacionService.buscarDonacionesPorId(id);
            return ResponseEntity.ok(pac);
        } catch (Exception e) {
            // Si no lo encuentra envio codigo not found
            return ResponseEntity.notFound().build();
        }
    }

    // Metodo para eliminar donaciones por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarDonacionesPorId(@PathVariable int id) {
        try {
            // Verificar si el Donacion existe
            Donaciones don = donacionService.buscarDonacionesPorId(id);
            donacionService.eliminarDonacion(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            // No existe el Donacion
            return ResponseEntity.notFound().build();
        }
    }

    // Metodo para actualizar una donacion por su ID
    @PutMapping("/{id}")
    public ResponseEntity<Donaciones> actualizarDonacionPorId(@PathVariable Integer id, @RequestBody Donaciones don) {
        try {
            // Verifico si la donación existe
            Donaciones donacion2 = donacionService.buscarDonacionesPorId(id);
            // Si no se encuentra, lanzo excepción manual para que vaya al catch
            if (donacion2 == null) {
                return ResponseEntity.notFound().build();
            }
            // Actualizo los campos
            donacion2.setIdDonacion(id);
            donacion2.setTituloLibro(don.getTituloLibro());
            donacion2.setAutorLibro(don.getAutorLibro());
            donacion2.setFechaDonacion(don.getFechaDonacion());
            donacion2.setEstadoLibro(don.getEstadoLibro());
            donacion2.setObservaciones(don.getObservaciones());
            // Guardar los cambios
            donacionService.guardarDonacion(donacion2);
            return ResponseEntity.ok(donacion2);

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
