package Microservicio.de.Control.de.roles.y.Permisos.Microservicio.de.roles.y.permisos.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Microservicio.de.Control.de.roles.y.Permisos.Microservicio.de.roles.y.permisos.Model.Permisos;
import Microservicio.de.Control.de.roles.y.Permisos.Microservicio.de.roles.y.permisos.Model.Rol;

import java.util.List;


@Repository
public interface PermisoRepository extends JpaRepository<Permisos, Long>{
    
    //Buscar todos los permisos asociados a un rol especifico
    List<Permisos> findByRol(Rol rol);

    //Buscar por nombre exacto del permiso
    List<Permisos> findByNombrePermiso(String nombrePermiso);

}
