package com.example.Microservicio.logistica.y.stock.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Microservicio.logistica.y.stock.Model.LibroStock;
import com.example.Microservicio.logistica.y.stock.Repository.LibroStockRepository;

@Service
public class LibroStockService {
    @Autowired
    private LibroStockRepository libroStockRepository;

    //Obtener todos los libros en stock
    public List<LibroStock> obtenerLibroStock(){
        return libroStockRepository.findAll();
    }

    //Buscar libro por ID
    public Optional<LibroStock> obtenerId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }
        return libroStockRepository.findById(id);
    }

    //Guardar un nuevo libro 
    public LibroStock guardarLibroStock(LibroStock libroStock){
        if (libroStock == null) {
        throw new IllegalArgumentException("El libro no puede ser nulo.");
        }
        if (libroStock.getNombreLibro() == null || libroStock.getNombreLibro().isBlank()) {
            throw new IllegalArgumentException("El nombre del libro no puede estar vacío.");
        }
        if (libroStock.getEstante() == null || libroStock.getEstante().isBlank()) {
            throw new IllegalArgumentException("El estante no puede estar vacío.");
        }
        if (libroStock.getFila() == null || libroStock.getFila().isBlank()) {
            throw new IllegalArgumentException("La fila no puede estar vacía.");
        }
        if (libroStock.getCantidad() < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa.");
        }

        return libroStockRepository.save(libroStock);
    }

    //Actualizar un libro
    public Optional<LibroStock> actualizar(Long id, LibroStock libroActualizado) {
    if (id == null || id <= 0) {
        throw new IllegalArgumentException("ID inválido.");
    }

    return libroStockRepository.findById(id).map(libro -> {
        if (libroActualizado.getNombreLibro() == null || libroActualizado.getNombreLibro().isBlank()) {
            throw new IllegalArgumentException("El nombre del libro no puede estar vacío.");
        }
        if (libroActualizado.getEstante() == null || libroActualizado.getEstante().isBlank()) {
            throw new IllegalArgumentException("El estante no puede estar vacío.");
        }
        if (libroActualizado.getFila() == null || libroActualizado.getFila().isBlank()) {
            throw new IllegalArgumentException("La fila no puede estar vacía.");
        }
        if (libroActualizado.getCantidad() < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa.");
        }

        libro.setNombreLibro(libroActualizado.getNombreLibro());
        libro.setEstante(libroActualizado.getEstante());
        libro.setFila(libroActualizado.getFila());
        libro.setCantidad(libroActualizado.getCantidad());
        return libroStockRepository.save(libro);
        });
    }

    //eliminar un libro por id
    public void eliminar(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }
        if (!libroStockRepository.existsById(id)) {
            throw new IllegalArgumentException("El libro con ID " + id + " no existe.");
        }
        libroStockRepository.deleteById(id);
    }
}
