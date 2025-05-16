package com.example.Microservicio.logistica.y.stock.Model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@Table(name = "movimiento_stock")

@Data
@NoArgsConstructor
@AllArgsConstructor

public class MovimientoStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMoviemientos;
    
    @Column(nullable = false)
    private int cantidad;

    @Column(nullable = false,length = 20)
    private String tipoMovimiento; //Entrada o salida de productos

    @Column(nullable = false)
    private String fechaMovimiento;

    @Column(length = 255)
    private String observacion;

    //Relacion muchos a uno con producto
    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    @JsonIgnoreProperties("movimientos")
    private Producto producto;
}
