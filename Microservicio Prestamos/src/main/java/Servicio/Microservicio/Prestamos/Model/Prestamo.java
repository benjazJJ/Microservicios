package Servicio.Microservicio.Prestamos.Model;

import java.sql.Date;

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
@Table(name = "prestamos")

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prestamo", unique = true, nullable = false)
    private Integer idPrestamo;

    @Column(nullable = false)
    private Long idLibro;

    @Column(unique = true, length = 12, nullable = false)
    private String runSolicitante;

    @Column(nullable = false)
    private Date fechaSolicitud;

    @Column(nullable = true)
    private Date fechaEntrega;
}
