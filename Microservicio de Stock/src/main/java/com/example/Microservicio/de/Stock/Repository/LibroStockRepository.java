package com.example.Microservicio.de.Stock.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Microservicio.de.Stock.Model.LibroStock;
import java.util.List;
import java.util.Optional;


public interface LibroStockRepository extends JpaRepository<LibroStock, Long>{
    
    List<LibroStock> findByNombreLibro(String nombreLibro); //Buscar Libro por nombre

    Optional<LibroStock> findByEstanteAndFila(String estante, String fila); //Buscar libro por estante y fila

}
