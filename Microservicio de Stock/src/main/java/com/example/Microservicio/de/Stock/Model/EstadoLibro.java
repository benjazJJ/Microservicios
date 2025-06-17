package com.example.Microservicio.de.Stock.Model;

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
@Table(name = "estado_libro")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstadoLibro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEstadoLibro;

    @Column(nullable = false, unique = true, length = 50)
    private String nombreEstado;
}
