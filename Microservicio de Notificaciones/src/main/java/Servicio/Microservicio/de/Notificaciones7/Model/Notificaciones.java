package Servicio.Microservicio.de.Notificaciones7.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "notificaciones") // Nombre de la tabla en la base de datos
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notificaciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notificacion")
    private int id;

    @Column(nullable = false, length = 255)
    private String mensaje; // Contenido del mensaje de la notificación

    @Column(nullable = false, length = 50)
    private String tipo; // Tipo de notificación: "Devolución", "Recordatorio", etc.

    @Column(nullable = false, length = 100)
    private String email; // Correo del destinatario para idenfiticar hacia quien va la notificación
}
