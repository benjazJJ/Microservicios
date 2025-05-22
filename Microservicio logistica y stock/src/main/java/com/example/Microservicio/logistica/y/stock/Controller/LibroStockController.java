package com.example.Microservicio.logistica.y.stock.Controller;

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

import com.example.Microservicio.logistica.y.stock.Model.LibroStock;
import com.example.Microservicio.logistica.y.stock.Repository.LibroStockRepository;

@RestController
@RequestMapping("/api/v1/librostock")
public class LibroStockController {
    @Autowired
    private LibroStockRepository libroStockRepository;
    
    //Obtener los libros en stock
    @GetMapping
    public List<LibroStock> obtenerLibroStock() {
        return libroStockRepository.findAll();
    }

    //Obtener libro por ID
    @GetMapping("/{id}")
    public ResponseEntity<LibroStock> obtenerLibroPorId(@PathVariable Long id){
        Optional<LibroStock> libroStock = libroStockRepository.findById(id);
        return libroStock.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Crear un nuevo registro de libro en stock
    @PostMapping
    public LibroStock crearLibroStock(@RequestBody LibroStock libroStock){
        return libroStockRepository.save(libroStock);
    }

    //Actualizar un  libro existente 
    @PutMapping("/{id}")
    public ResponseEntity<LibroStock> actualizar(@PathVariable Long id, @RequestBody LibroStock libroActualizado) {
        return libroStockRepository.findById(id)
                .map(libro -> {
                    libro.setNombreLibro(libroActualizado.getNombreLibro());
                    libro.setEstante(libroActualizado.getEstante());
                    libro.setFila(libroActualizado.getFila());
                    libro.setCantidad(libroActualizado.getCantidad());
                    libroStockRepository.save(libro);
                    return ResponseEntity.ok(libro);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    // Eliminar un libro por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        if (libroStockRepository.existsById(id)) {
            libroStockRepository.deleteById(id);
            return ResponseEntity.ok("Libro eliminado del stock");
        } else {
            return ResponseEntity.status(404).body("Libro no encontrado");
        }
    }
}
