package Servicio.Microservicio.de.Autenticacion9.Repository;

import Servicio.Microservicio.de.Autenticacion9.Model.Autenticacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AutenticacionRepository extends JpaRepository<Autenticacion, Integer> {
    Optional<Autenticacion> findByCorreoAndContrasena(String correo, String contrasena);
}
