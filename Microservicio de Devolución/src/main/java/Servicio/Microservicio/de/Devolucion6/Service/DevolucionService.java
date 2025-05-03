package Servicio.Microservicio.de.Devolucion6.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Servicio.Microservicio.de.Devolucion6.Model.Devolucion;
import Servicio.Microservicio.de.Devolucion6.Repository.DevolucionRepositoryx;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class DevolucionService {

    @Autowired
    private DevolucionRepositoryx devolucionRepository;

    // Devuelve todas las devoluciones
    public List<Devolucion> listarDevoluciones() {
        return devolucionRepository.findAll();
    }

    // Busca una devolución por su ID
    public Devolucion buscarDevolucionPorID(int idDevolucion) {
        return devolucionRepository.findById(idDevolucion).get();
    }

    //funcion para guardar una devolucion
    public Devolucion saveDevolucion(Devolucion devolucion){
        return devolucionRepository.save(devolucion);
    }

    // Elimina una devolución por su ID
    public void borrarDevolucion(int idDevolucion){
        devolucionRepository.deleteById(idDevolucion);
    }
}
