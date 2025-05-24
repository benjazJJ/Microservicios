package Servicio.Microservicio.Prestamos.Service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Servicio.Microservicio.Prestamos.Model.Prestamo;
import Servicio.Microservicio.Prestamos.Repository.PrestamoRepository;
import Servicio.Microservicio.Prestamos.webclient.PedidoPed;

@Service
public class PrestamoService {

    // Aquí puedes implementar la lógica de negocio relacionada con los préstamos.
    // Por ejemplo, métodos para crear, leer, actualizar y eliminar préstamos.
    
    @Autowired
    private PrestamoRepository prestamoRepository;

    @Autowired
    private PedidoPed PedidoPed;

    //Metodo para crear un nuevo Prestamo
    public Prestamo crearPrestamo(Prestamo prestamo) {
        //verificar si el Libro existe consultando al microservicio Logistica y stock
        Map<String,Object> Libro = PedidoPed.getLibroById(prestamo.getIdLibro());
        //verifico si me trajo el Libro o no
        if(Libro == null || Libro.isEmpty()){
            throw new RuntimeException("Libro no encontrado. No se puede agregar el prestamo");
        }
        return prestamoRepository.save(prestamo);
        
    }

    //Metodo para obtener todos los prestamos
    public List<Prestamo> obtenerTodosLosPrestamos() {
        return prestamoRepository.findAll();
    }

    //Metodo para obtener un prestamo por su id
    public Prestamo obtenerPrestamoPorId(Integer idPrestamo) {
        return prestamoRepository.findById(idPrestamo).orElse(null);
    }

    //Metodo para obtener un prestamo por el Run del solicitante
    public List<Prestamo> obtenerPrestamosPorRun(String runSolicitante) {
        return prestamoRepository.findByRunSolicitante(runSolicitante);
    }

    //Metodo para obtener un prestamo pendiente (Sin fecha de entrega)
    public List<Prestamo> obtenerPrestamoPendientes(){
        return prestamoRepository.findByFechaEntregaIsNull();
    }

    //Metodo para actualizar un prestamo existente
    public Prestamo actualizarPrestamo(Prestamo prestamo){
        return prestamoRepository.save(prestamo);
    }

    //Metodo para eliminar un prestamo por su ID
    public void eliminarPrestamo(Integer idPrestamo){
        prestamoRepository.deleteById(idPrestamo);
    }

}
