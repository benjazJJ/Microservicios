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

    // Metodo para crear un nuevo Prestamo
    public Prestamo crearPrestamo(Prestamo prestamo) {
        // 1. Validar que el usuario existe por ID
        if (!cuentasClient.validarUsuarioPorId(prestamo.getIdUsuario())) {
            throw new RuntimeException("No existe un usuario con el ID: " + prestamo.getIdUsuario());
        }

        // 2. Validar que el usuario existe por RUT
        if (!cuentasClient.validarUsuarioPorRut(prestamo.getRunSolicitante())) {
            throw new RuntimeException("No existe un usuario con el RUT: " + prestamo.getRunSolicitante());
        }

        // 3. Validar si ya tiene préstamo por ID
        if (prestamoRepository.existsByIdUsuario(prestamo.getIdUsuario())) {
            throw new RuntimeException("Ya existe un préstamo asignado al ID de usuario: " + prestamo.getIdUsuario());
        }

        // 4. Validar si ya tiene préstamo por RUT
        if (prestamoRepository.existsByRunSolicitante(prestamo.getRunSolicitante())) {
            throw new RuntimeException("Ya existe un préstamo asignado al RUT: " + prestamo.getRunSolicitante());
        }

        // 5. Verificar que el libro exista
        Map<String, Object> libro = PedidoPed.getLibroById(prestamo.getIdLibro());

        if (libro == null || libro.isEmpty()) {
            throw new RuntimeException("Libro no encontrado. No se puede registrar el préstamo.");
        }

        // 6. Verificar stock
        int cantidad = Integer.parseInt(libro.get("cantidad").toString());
        if (cantidad < 1) {
            throw new RuntimeException("El libro no está disponible. No hay stock suficiente.");
        }

        return prestamoRepository.save(prestamo);
    }

    public List<Prestamo> obtenerTodosLosPrestamos() {
        return prestamoRepository.findAll();
    }

    public Prestamo obtenerPrestamoPorId(Integer idPrestamo) {
        return prestamoRepository.findById(idPrestamo).orElse(null);
    }

    public List<Prestamo> obtenerPrestamosPorRun(String runSolicitante) {
        return prestamoRepository.findByRunSolicitante(runSolicitante);
    }

    public List<Prestamo> obtenerPrestamoPendientes() {
        return prestamoRepository.findByFechaEntregaIsNull();
    }

    public Prestamo actualizarPrestamo(Prestamo prestamo) {
        return prestamoRepository.save(prestamo);
    }

    public void eliminarPrestamo(Integer idPrestamo) {
        prestamoRepository.deleteById(idPrestamo);
    }
}
