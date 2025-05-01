package Servicio.Microservicio.de.Administracion.de.Contenidos.y.Multimedia4.Repository;

import Servicio.Microservicio.de.Administracion.de.Contenidos.y.Multimedia4.Model.Contenido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContenidoRepository extends JpaRepository<Contenido, Integer> {
    // Puedes agregar métodos personalizados aquí si los necesitas
}
