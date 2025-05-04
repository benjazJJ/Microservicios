package Servicio.Microservicio.Prestamos.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Servicio.Microservicio.Prestamos.Model.Prestamo;
import Servicio.Microservicio.Prestamos.Repository.PrestamoRepository;

@Service
public class PrestamoService {

    // Aquí puedes implementar la lógica de negocio relacionada con los préstamos.
    // Por ejemplo, métodos para crear, leer, actualizar y eliminar préstamos.
    
    @Autowired
    private PrestamoRepository prestamoRepository;
    
    //Metodo para crear un nuevo Prestamo
    public Prestamo crearPrestamo(Prestamo prestamo) {
        return prestamoRepository.save(prestamo);
    }

    //Metodo para obtener todos los prestamos
    public List<Prestamo> obtenerTodosLosPrestamos() {
        return prestamoRepository.findAll();
    }

    //Metodo para obtener un prestamo por su id
    public Prestamo obtenerPrestamoPorId(Integer idPrestamo) {
        return prestamoRepository.findByIdPrestamo(idPrestamo).orElse(null);
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
