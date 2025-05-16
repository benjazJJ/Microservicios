package com.example.Microservicio.logistica.y.stock.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Microservicio.logistica.y.stock.Model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>{
    //Buscar producto por nombre
    Optional<Producto> findByNombreIgnoreCase(String nombre);

} 


