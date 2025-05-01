package Servicio.Microservicio.de.Catalogo.de.Libros2.Repository;

import Servicio.Microservicio.de.Catalogo.de.Libros2.Model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Integer> {
    
}
