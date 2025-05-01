package Servicio.Microservicio.de.Inicio.de.Sesion8.Repository;

import Servicio.Microservicio.de.Inicio.de.Sesion8.Model.LoginRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<LoginRequest, Integer> {

    // Buscar por correo y contraseña
    Optional<LoginRequest> findByCorreoAndContrasena(String correo, String contrasena);
}
