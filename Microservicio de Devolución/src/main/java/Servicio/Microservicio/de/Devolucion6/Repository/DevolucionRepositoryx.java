package Servicio.Microservicio.de.Devolucion6.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Servicio.Microservicio.de.Devolucion6.Model.Devolucion;

@Repository
public interface DevolucionRepositoryx extends JpaRepository<Devolucion, Integer> {

    // Buscar todas las devoluciones de un usuario por su ID
    @Query("SELECT d FROM Devolucion d WHERE d.idUsuario = :idUsuario")
    List<Devolucion> buscarPorIdUsuario(@Param("idUsuario") int idUsuario);

    // Buscar todas las devoluciones de un libro por su ID
    @Query("SELECT d FROM Devolucion d WHERE d.idLibro = :idLibro")
    List<Devolucion> buscarPorIdLibro(@Param("idLibro") int idLibro);

    // Buscar todas las devoluciones por estado del libro
    @Query("SELECT d FROM Devolucion d WHERE d.estadoLibro = :estado")
    List<Devolucion> buscarPorEstadoLibro(@Param("estado") String estado);
}
