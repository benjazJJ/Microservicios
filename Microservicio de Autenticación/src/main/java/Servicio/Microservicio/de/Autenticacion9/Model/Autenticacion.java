package Servicio.Microservicio.de.Autenticacion9.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "autenticacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Autenticacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_login")
    private int idLogin;

    @Column(nullable = false, unique = true, length = 100)
    private String correo;

    @Column(nullable = false, length = 100)
    private String contrasena;
}
