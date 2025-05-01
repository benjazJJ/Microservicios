package Servicio.Microservicio.de.Inicio.de.Sesion8.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "login_request") // Nombre de la tabla en la base de datos
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID autoincremental
    @Column(name = "id_login")
    private int idLogin;

    @Column(name = "correo", nullable = false, unique = true, length = 100)
    private String correo; // Correo ingresado

    @Column(name = "contrasena", nullable = false, length = 100)
    private String contrasena; // Contraseña ingresada
}
