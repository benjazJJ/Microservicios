package Servicio.Microservicio.Prestamos.Service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Servicio.Microservicio.Prestamos.Model.Prestamo;
import Servicio.Microservicio.Prestamos.Repository.PrestamoRepository;
import Servicio.Microservicio.Prestamos.webclient.PedidoPed;
import Servicio.Microservicio.Prestamos.webclient.CuentasClient;

@Service
public class PrestamoService {

    @Autowired
    private PrestamoRepository prestamoRepository;

    @Autowired
    private PedidoPed PedidoPed;

    @Autowired
    private CuentasClient cuentasClient;

    //Metodo para crear un nuevo Prestamo
    public Prestamo crearPrestamo(Prestamo prestamo) {
        // Validar que el usuario exista en el microservicio Cuentas
        

        // Consultar libro por ID
        Map<String,Object> libro = PedidoPed.getLibroById(prestamo.getIdLibro());

        // Verificar si el libro existe
        if (libro == null || libro.isEmpty()) {
            throw new RuntimeException("Libro no encontrado. No se puede agregar el préstamo.");
        }

        // Obtener cantidad y validar
        Object cantidadObj = libro.get("cantidad");
        int cantidad = Integer.parseInt(cantidadObj.toString());

        if (cantidad < 1) {
            throw new RuntimeException("El libro no está disponible. No hay stock suficiente.");
        }

        // Guardar el préstamo
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

