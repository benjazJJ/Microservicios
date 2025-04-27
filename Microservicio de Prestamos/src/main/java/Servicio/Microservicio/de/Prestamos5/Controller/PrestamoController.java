package Servicio.Microservicio.de.Prestamos5.Controller;

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

import Servicio.Microservicio.de.Prestamos5.Model.Prestamo;
import Servicio.Microservicio.de.Prestamos5.Service.PrestamoService;

@RestController
@RequestMapping("/api/v1/prestamo")
public class PrestamoController {
    @Autowired
    PrestamoService prestamoService;

    @GetMapping
    public List<Prestamo> listarPrestamos(){
        return prestamoService.getPrestamo();
    }
    
    @GetMapping("{id}")
    public Prestamo obtenerPrestamo(@PathVariable int id){
        return prestamoService.getPrestamoId(id);
    }

    @PostMapping
    public String guardarPrestamo (@RequestBody Prestamo pres){
        return prestamoService.savePrestamo(pres);
    }

    @PutMapping("{id}")
    public List<Prestamo> modificarPrestamo (@PathVariable int id, @RequestBody Prestamo pres){
        return prestamoService.updatePrestamo(pres);
    }

    @DeleteMapping("{id}")
    public String eliminarPrestamo(@PathVariable int id){
        return prestamoService.deletePrestamo(id);
    }
}
