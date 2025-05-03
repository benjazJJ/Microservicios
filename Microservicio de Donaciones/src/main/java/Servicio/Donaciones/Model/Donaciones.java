package Servicio.Donaciones.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "donacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Donaciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_donacion")
    private int idDonacion;                   // ID único de la donación

    @Column(name = "titulo_libro", nullable = false, length =  100)
    private String tituloLibro;               // Título del libro donado

    @Column(name = "autor_libro", nullable = false, length =  100)
    private String autorLibro;                // Autor del libro

    @Column(name = "fecha_donacion", nullable = false)
    private Date fechaDonacion;               // Fecha en que se realizó la donación

    @Column(name = "estado_libro", nullable = false, length =  100)
    private String estadoLibro;               // Estado del libro donado

    @Column(name = "observaciones", nullable = true, length =  100)
    private String observaciones;             // Comentarios adicionales

}

