package com.example.Microservicio.logistica.y.stock.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Microservicio.logistica.y.stock.Model.MovimientoStock;

@Repository
public interface MovimientoStrockRepository extends JpaRepository<MovimientoStock, Long> {
    //obtener movimientos asociados a un producto
    List<MovimientoStock> findByProductoId(Long productoId);
}
