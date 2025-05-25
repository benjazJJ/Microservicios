package Servicio.Microservicio.de.Devolucion6.Service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Servicio.Microservicio.de.Devolucion6.Model.Devolucion;
import Servicio.Microservicio.de.Devolucion6.Repository.DevolucionRepositoryx;
import Servicio.Microservicio.de.Devolucion6.WebClient.DevolucionDev;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class DevolucionService {

    @Autowired
    private DevolucionRepositoryx devolucionRepository;

    @Autowired
    private DevolucionDev devolucionDev;

    // Devuelve todas las devoluciones
    public List<Devolucion> listarDevoluciones() {
        return devolucionRepository.findAll();
    }

    // Busca una devolución por su ID
    public Devolucion buscarDevolucionPorID(Integer id) {
        return devolucionRepository.findById(id).get();
    }

    //funcion para guardar una devolucion
    public Devolucion crearDevolucion(Devolucion devolucion) {
        //verificar si el prestamo existe consultando al microservicio Prestamo
        Map<String,Object> Prestamo = devolucionDev.getPrestamoById(devolucion.getIdPrestamo());
        //verifico si me trajo el Prestamo o no
        if(Prestamo == null || Prestamo.isEmpty()){
            throw new RuntimeException("Prestamo no encontrado. No se puede agregar la devolucion");
        }
        return devolucionRepository.save(devolucion);
        
    }

    // Elimina una devolución por su ID
    public void borrarDevolucion(Integer idDevolucion){
        devolucionRepository.deleteById(idDevolucion);
    }
}
