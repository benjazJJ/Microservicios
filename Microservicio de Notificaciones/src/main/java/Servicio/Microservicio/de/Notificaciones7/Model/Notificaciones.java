package Servicio.Microservicio.de.Notificaciones7.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "notificaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notificaciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notificacion")
    private int id;

    @Column(nullable = false, length = 255)
    private String mensaje;

    @Column(nullable = false, length = 50)
    private String tipo;

    @Column(nullable = false, length = 100)
    private String correoReceptor;

    @Column(nullable = false, length = 100)
    private String correoEmisor;
}
