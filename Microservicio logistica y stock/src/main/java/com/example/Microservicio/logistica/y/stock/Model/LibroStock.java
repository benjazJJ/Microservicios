package com.example.Microservicio.logistica.y.stock.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "libro_stock")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibroStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLibroStock; //ID del libro en stock

    @Column(nullable = false, length = 150, name = "nombre_libro")
    private String nombreLibro; //Nombre del libro
    
    @Column(nullable = false, length = 20)
    private String estante; //estante donde se encuentra el libro

    @Column(nullable = false, length = 10)
    private String fila; //fila donde se encuentra el libro

    @Column(nullable = false, length = 10)
    private int cantidad; //cantidad de libros en stock

    
}
