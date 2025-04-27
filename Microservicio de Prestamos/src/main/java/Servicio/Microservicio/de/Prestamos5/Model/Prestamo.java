package Servicio.Microservicio.de.Prestamos5.Model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class Prestamo {
    
    private int idPrestamo;
    private int idLibro;
    private String runSolicitante;
    private Date fechaSolicitud;
    private Date fechaEntrega = null;
    private int cantidadDias;
    private int multas = 0;

}
