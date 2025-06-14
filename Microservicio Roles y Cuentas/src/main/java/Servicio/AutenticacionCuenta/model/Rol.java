package main.java.Servicio.AutenticacionCuenta.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRol;

    @Column(nullable = false, unique = true)
    private String nombreRol; // ADMINISTRADOR, DOCENTE, BIBLIOTECARIO, ESTUDIANTE
}
