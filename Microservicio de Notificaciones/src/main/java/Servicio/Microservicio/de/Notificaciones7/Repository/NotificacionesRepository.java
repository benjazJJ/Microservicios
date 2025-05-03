package Servicio.Microservicio.de.Notificaciones7.Repository;

import Servicio.Microservicio.de.Notificaciones7.Model.Notificaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificacionesRepository extends JpaRepository<Notificaciones, Integer> {
    //Acá podemos poner metodos que vayamos a usar @Gustavo @Benja
}
