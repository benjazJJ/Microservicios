package Servicio.Microservicio.de.Autenticacion9.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "carrito")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Autenticacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carrito")
    private int id_carrito;

    @Column(nullable = false, unique = true, length = 100)
    private int idLibro;

    @Column(nullable = false, length = 100)
    private int cantidad;
}
