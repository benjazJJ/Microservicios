package Servicio.Microservicio.de.Administracion.de.Contenidos.y.Multimedia4.Repository;

import Servicio.Microservicio.de.Administracion.de.Contenidos.y.Multimedia4.Model.Multimedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MultimediaRepository extends JpaRepository<Multimedia, Integer> {
    
}
