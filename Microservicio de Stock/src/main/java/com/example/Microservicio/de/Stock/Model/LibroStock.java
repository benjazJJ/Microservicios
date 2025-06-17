package com.example.Microservicio.de.Stock.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    private Long idLibroStock; // Id del libro en stock

    @Column(nullable = false, length = 50, name = "nombre_libro")
    private String nombreLibro;// Nombre del libro

    @Column(nullable = false, length = 20)
    private String estante; // Estante donde se encuentra el libro

    @Column(nullable = false, length = 10)
    private String fila; // fila donde se encuentra el libro

    @Column(nullable = false, length = 10)
    private int cantidad; // Cantidad de libros en stock

    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "id_estado", nullable = false)
    private EstadoLibro estado;

}
