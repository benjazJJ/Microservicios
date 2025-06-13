package com.example.Microservicio.Roles.y.Permisos.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Microservicio.Roles.y.Permisos.Model.Permisos;
import com.example.Microservicio.Roles.y.Permisos.Model.Rol;

import java.util.List;


@Repository
public interface PermisoRepository extends JpaRepository<Permisos, Long> {

    //Buscar todos los permisos asociados a un rol especifico
    List<Permisos> findByRol(Rol rol);

    //Buscar por nombre exacto del permiso
    List<Permisos> findByNombrePermiso(String nombrePermiso);
}
