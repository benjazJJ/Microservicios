package Microservicio.Microservicio.Roles.Y.Permisos.Model;

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
    @Column(name = "id_rol")
    private int idRol;

    @Column(nullable = false, unique = true)
    private String nombreRol; // ADMINISTRADOR, DOCENTE, BIBLIOTECARIO, ESTUDIANTE
}