package Servicio.Microservicio.de.Devolucion6.Controller;

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
import org.springframework.web.bind.annotation.RestController;

import Servicio.Microservicio.de.Devolucion6.Model.Devolucion;
import Servicio.Microservicio.de.Devolucion6.Service.DevolucionService;

@RestController
@RequestMapping("/api/v1/devoluciones")
public class DevolucionController {

    @Autowired
    private DevolucionService devolucionService;

    @GetMapping
    public ResponseEntity<List<Devolucion>> listar(){
        //Guardar en una lista nueva los elementos
        List<Devolucion> devolucion = devolucionService.listarDevoluciones();
        if(devolucion.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(devolucion);
    }

    // crear una nueva devolucion
    @PostMapping
    public ResponseEntity<?> crearnuevaDevolucion(@RequestBody Devolucion nuevaDevolucion){
        try {
            Devolucion devolucion = devolucionService.crearDevolucion(nuevaDevolucion);
            return ResponseEntity.status(201).body(devolucion);
        } catch (RuntimeException e) {
            // TODO: handle exception
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    //Metodo para buscar Devolucion por Id
    @GetMapping("/{id}")
    public ResponseEntity<Devolucion> buscarDevolucionPorId(@PathVariable Integer id){
        try {
            //Verificar si el Devolucion existe
            Devolucion dev = devolucionService.buscarDevolucionPorID(id);
            return ResponseEntity.ok(dev);
        } catch (Exception e) {
            // Si no lo encuentra envio codigo not found
            return ResponseEntity.notFound().build();
        }
    }

    //Metodo para eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarDevolucion(@PathVariable int id){
        try {
            //Verificar si la devolucion existe
            Devolucion dev = devolucionService.buscarDevolucionPorID(id);
            devolucionService.borrarDevolucion(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            // No existe la devolucion
            return ResponseEntity.notFound().build();
        }
    }

    //Metodo para actualizar una devolucion por su id
    @PutMapping("/{id}")
    public ResponseEntity<Devolucion> actualizarDevolucionPorID(@PathVariable Integer id, @RequestBody Devolucion dev){
        try {
            //Verifico si el paciente existe
            Devolucion devolucion2 = devolucionService.buscarDevolucionPorID(id);
            if (devolucion2== null) {
                return ResponseEntity.notFound().build();
            }
            //Si existe el paciente modifico sus valores
            devolucion2.setIdDevolucion(id);
            devolucion2.setFechaDevolucion(dev.getFechaDevolucion());
            devolucion2.setEstadoLibro(dev.getEstadoLibro());
            devolucion2.setObservaciones(dev.getObservaciones());

            //Actualizar Registro
            devolucionService.crearDevolucion(devolucion2);
            return ResponseEntity.ok(devolucion2);

        } catch (Exception e) {
            // Si no encuentra la devolucion
            return ResponseEntity.notFound().build();
        }
    }



}
