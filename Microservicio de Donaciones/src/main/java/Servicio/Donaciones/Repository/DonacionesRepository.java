package Servicio.Donaciones.Repository;

import Servicio.Donaciones.Model.Donaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonacionesRepository extends JpaRepository<Donaciones, Integer> {

    // Buscar donaciones por autor del libro, ignorando mayúsculas y minúsculas
    @Query("SELECT d FROM Donaciones d WHERE LOWER(d.autorLibro) = LOWER(:autor)")
    List<Donaciones> buscarPorAutor(@Param("autor") String autor);
    
}