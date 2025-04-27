package Servicio.Microservicio.de.Prestamos5.Repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import Servicio.Microservicio.de.Prestamos5.Model.Prestamo;
import java.util.ArrayList;
import java.sql.Date;
import java.time.LocalDate;

@Repository

public class PrestamoRepository {
     public List<Prestamo> listaPrestamos = new ArrayList<>();

    // Constructor para agregar Prestamos iniciales
    public PrestamoRepository() {
        listaPrestamos.add(new Prestamo(1, 1, "12345678-9", Date.valueOf(LocalDate.of(2025, 4, 27)), null, 14, 0));
        listaPrestamos.add(new Prestamo(2, 2, "98765432-1", Date.valueOf(LocalDate.of(2025, 4, 29)), null, 7, 0));
    }

    // Método para obtener todos los Prestamos
    public List<Prestamo> buscarPrestamos() {
        return listaPrestamos;
    }

    // Método para buscar un Prestamo por ID
    public Prestamo buscarPrestamoPorId(int id) {
        for (Prestamo prestamo : listaPrestamos) {
            if (prestamo.getIdLibro() == id) {
                return prestamo;
            }
        }
        return null;
    }

    // Método para guardar un Prestamo
    public Prestamo guardarPrestamo(Prestamo pres) {
        validarRut(pres.getRunSolicitante());
        listaPrestamos.add(pres);
        return pres;
    }

    // Método para modificar un Prestamo
    public Prestamo modificarPrestamo(Prestamo pres) {
        validarRut(pres.getRunSolicitante());

        int posicion = -1;
        int id = 0;

        // Recorrer la lista para encontrar la posición del elemento
        for (int x = 0; x < listaPrestamos.size(); x++) {
            if (listaPrestamos.get(x).getIdPrestamo() == pres.getIdPrestamo()) {
                posicion = x;
                id = listaPrestamos.get(x).getIdPrestamo();
                break;
            }
        }

        if (posicion == -1) {
            throw new IllegalArgumentException("Préstamo no encontrado para modificar.");
        }

        // Crear un nuevo objeto y anexar los nuevos valores
        Prestamo pres1 = new Prestamo();
        pres1.setIdPrestamo(id);
        pres1.setIdLibro(pres.getIdLibro());
        pres1.setRunSolicitante(pres.getRunSolicitante());
        pres1.setFechaSolicitud(pres.getFechaSolicitud());
        pres1.setFechaEntrega(pres.getFechaEntrega());
        pres1.setCantidadDias(pres.getCantidadDias());
        pres1.setMultas(pres.getMultas());

        // Actualizar el elemento en la posición encontrada
        listaPrestamos.set(posicion, pres1);
        return pres1;
    }

    // Método para eliminar un Prestamo
    public void eliminarPrestamo(int id) {
        listaPrestamos.removeIf(x -> x.getIdPrestamo() == id);
    }

    // Método para validar el formato del RUT
    private void validarRut(String runSolicitante) {
        if (runSolicitante == null || !runSolicitante.matches("^\\d{7,8}-[\\dkK]{1}$")) {
            throw new IllegalArgumentException("RUT inválido. Debe tener 7 u 8 dígitos, guion y dígito verificador (número o K).");
        }
    }
}



