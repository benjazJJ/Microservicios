package Microservicio.Solicitudes.de.Ayuda.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Microservicio.Solicitudes.de.Ayuda.Model.SolicitudAyuda;

import java.util.List;

@Repository
public interface SolicitudAyudaRepository extends JpaRepository<SolicitudAyuda, Integer> {
    List<SolicitudAyuda> findByCorreoUsuario(String correoUsuario); // Filtro por usuario
}

