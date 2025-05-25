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
    @Column(name = "id_devolucion")
    private int idDevolucion;         // ID único de la devolución
    @Column(name= "fecha_devolucion", nullable = false)
    private Date fechaDevolucion;     // Fecha en que se devolvió el libro
    @Column(name= "estado_libro", nullable = false, length = 100) 
    private String estadoLibro;       // Estado del libro al ser devuelto (Ej: Bueno, Dañado, Perdido)
    @Column(name= "observaciones", nullable = true, length = 100)
    private String observaciones;     // Comentarios adicionales sobre la devolución
    @Column(name= "id_prestamo", nullable = true, length = 100)
    private Long idPrestamo;      // ID del préstamo asociado a la devolución
}