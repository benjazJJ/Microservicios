package Servicio.AutenticacionCuenta.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import Servicio.AutenticacionCuenta.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByCorreo(String correo);

    Optional<Usuario> findByRut(String rut);

    Optional<Usuario> findById(Integer id);

}
