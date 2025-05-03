package Servicio.Microservicio.de.Notificaciones7.Service;

import Servicio.Microservicio.de.Notificaciones7.Model.Notificaciones;
import Servicio.Microservicio.de.Notificaciones7.Repository.NotificacionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificacionesService {

    @Autowired
    private NotificacionesRepository notificacionesRepository;

    // Obtener todas las notificaciones
    public List<Notificaciones> obtenerTodas() {
        return notificacionesRepository.findAll();
    }

    // Obtener una notificación por ID
    public Optional<Notificaciones> obtenerPorId(int id) {
        return notificacionesRepository.findById(id);
    }

    // Guardar una nueva notificación
    public Notificaciones guardar(Notificaciones notificacion) {
        return notificacionesRepository.save(notificacion);
    }

    // Actualizar una notificación existente
    public Notificaciones actualizar(int id, Notificaciones notificacionActualizada) {
        notificacionActualizada.setId(id);
        return notificacionesRepository.save(notificacionActualizada);
    }

    // Eliminar una notificación por ID
    public String eliminar(int id) {
        if (notificacionesRepository.existsById(id)) {
            notificacionesRepository.deleteById(id);
            return "Notificación eliminada correctamente";
        } else {
            return "No se encontró la notificación con el ID proporcionado";
        }
    }
}
