package com.example.Microservicio.Roles.y.Permisos.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Microservicio.Roles.y.Permisos.Model.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    
    //Buscar un rol por su nombre
    Optional<Rol> findByNombreRol(String nombreRol);

    //Verificar si un rol existe por nombre
    boolean existsByNombreRol(String nombreRol);
}
