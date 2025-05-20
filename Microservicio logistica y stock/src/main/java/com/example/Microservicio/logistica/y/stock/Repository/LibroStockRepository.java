package com.example.Microservicio.logistica.y.stock.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Microservicio.logistica.y.stock.Model.LibroStock;

@Repository
public interface LibroStockRepository extends JpaRepository<LibroStock, Long> {
    
    List<LibroStock> findByNombreLibro(String nombreLibro); //Buscar libro por nombre

    Optional<LibroStock> findByEstanteAndFila(String estante, String fila);

}
