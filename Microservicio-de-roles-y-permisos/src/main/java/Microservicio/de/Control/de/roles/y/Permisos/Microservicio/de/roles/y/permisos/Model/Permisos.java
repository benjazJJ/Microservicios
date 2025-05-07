package Microservicio.de.Control.de.roles.y.Permisos.Microservicio.de.roles.y.permisos.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "permisos")

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Permisos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 30)
    private String nombrePermiso;

    @ManyToOne
    @JoinColumn(name = "rol_id", nullable = false) // Nombre real de la columna en la tabla permisos
    @JsonIgnoreProperties("permisosList")
    private Rol rol;
}
