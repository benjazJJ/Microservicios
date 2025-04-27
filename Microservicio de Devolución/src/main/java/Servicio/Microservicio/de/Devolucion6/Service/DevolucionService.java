package Servicio.Microservicio.de.Devolucion6.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Servicio.Microservicio.de.Devolucion6.Model.Devolucion;
import Servicio.Microservicio.de.Devolucion6.Repository.DevolucionRepository;

@Service
public class DevolucionService {

    @Autowired
    private DevolucionRepository devolucionRepository;

    // Devuelve todas las devoluciones
    public List<Devolucion> getDevoluciones() {
        return devolucionRepository.obtenerDevoluciones();
    }

    // Guarda una nueva devolución
    public Devolucion saveDevolucion(Devolucion devolucion) {
        return devolucionRepository.guardarDevolucion(devolucion);
    }

    // Busca una devolución por su ID
    public Devolucion getDevolucionID(int idDevolucion) {
        return devolucionRepository.buscarPorIdDevolucion(idDevolucion);
    }

    // Actualiza una devolución existente
    public Devolucion updateDevolucion(Devolucion devolucion) {
        return devolucionRepository.actualizarDevolucion(devolucion);
    }

    // Elimina una devolución por su ID
    public String deleteDevolucion(int idDevolucion) {
        devolucionRepository.eliminarDevolucion(idDevolucion);
        return "Devolución eliminada correctamente";
    }
}
