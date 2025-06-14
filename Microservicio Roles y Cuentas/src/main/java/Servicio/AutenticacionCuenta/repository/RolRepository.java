package main.java.Servicio.AutenticacionCuenta.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import Servicio.AutenticacionCuenta.model.Rol;

public interface RolRepository extends JpaRepository<Rol, Integer> {

    // Buscar por nombre del rol (ej: "ESTUDIANTE", "DOCENTE", "ADMINISTRADOR", "BIBLIOTECARIO")
    Optional<Rol> findByNombre(String nombreRol);
}