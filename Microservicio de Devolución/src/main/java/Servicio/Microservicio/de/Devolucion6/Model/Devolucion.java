package Servicio.Microservicio.de.Devolucion6.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "devolucion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Devolucion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDevolucion;         // ID único de la devolución

    @Column( nullable = false)
    private Date fechaDevolucion;     // Fecha en que se devolvió el libro

    @Column(nullable = false, length = 100) 
    private String estadoLibro;       // Estado del libro al ser devuelto (Ej: Bueno, Dañado, Perdido)

    @Column(nullable = true, length = 100)
    private String observaciones;     // Comentarios adicionales sobre la devolución

    @Column(nullable = false)
    private Long idPrestamo;      // ID del préstamo asociado a la devolución
}