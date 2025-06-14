package Microservicio.Microservicio.Roles.Y.Permisos.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Cuentas_usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private int id;

    @Column(name = "correo", nullable = false, unique = true, length = 100)
    private String correo;

    @Column(name = "contrasena", nullable = false, length = 200)
    private String contrasena;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "telefono", length = 20)
    private String telefono;

    @Column(name = "rut", nullable = false, unique = true, length = 12)
    private String rut;

    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false)
    private Rol rol;
}