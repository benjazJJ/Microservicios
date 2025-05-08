package Microservicio.de.Control.de.roles.y.Permisos.Microservicio.de.roles.y.permisos.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Microservicio.de.Control.de.roles.y.Permisos.Microservicio.de.roles.y.permisos.Model.Rol;
import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Rol, Long>{

    //Buscar un rol por su nombre
    Optional<Rol> findByNombreRol(String nombreRol);

    //Verificar si un rol existe por nombre
    boolean existsByNombreRol(String nombreRol);
}
