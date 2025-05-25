package com.example.Microservicio.Multas.Model;

import java.time.LocalDate;

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
@Table(name = "multa")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Multa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//Id de la multa

    @Column(nullable = false, length = 10)
    private String runUsuario; //RUN del usuario

    @Column(nullable = false)
    private LocalDate fechaInicio; //Fecha de inicio de la multa

    @Column(nullable = false)
    private LocalDate fechaFin; //Fecha de fin de la multa

    @Column(nullable = false, length = 100)
    private String motivo; //Motivo de la multa
}
