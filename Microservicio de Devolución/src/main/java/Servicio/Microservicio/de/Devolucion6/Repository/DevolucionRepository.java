package Servicio.Microservicio.de.Devolucion6.Repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import Servicio.Microservicio.de.Devolucion6.Model.Devolucion;

@Repository
public class DevolucionRepository {

    private List<Devolucion> listaDevoluciones = new ArrayList<>();

    // Constructor: Agrega ejemplos de devoluciones al iniciar
    public DevolucionRepository() {
        listaDevoluciones.add(new Devolucion(1, 101, 1001, new java.sql.Date(System.currentTimeMillis()), "Bueno", "Sin daños visibles"));
        listaDevoluciones.add(new Devolucion(2, 102, 1002, new java.sql.Date(System.currentTimeMillis()), "Dañado", "Cubierta rota"));
    }

    // Devuelve todas las devoluciones
    public List<Devolucion> obtenerDevoluciones() {
        return new ArrayList<>(listaDevoluciones);
    }

    // Busca una Devolucion por su ID
    public Devolucion buscarPorIdDevolucion(int idDevolucion) {
        for (Devolucion devolucion : listaDevoluciones) {
            if (devolucion.getIdDevolucion() == idDevolucion) {
                return devolucion;
            }
        }
        return null;
    }

    // Guarda una nueva devolucion, validando que no exista un ID duplicado
    public Devolucion guardarDevolucion(Devolucion devolucion) {
        if (buscarPorIdDevolucion(devolucion.getIdDevolucion()) != null) {
            throw new IllegalArgumentException("Ya existe una devolución con el ID " + devolucion.getIdDevolucion());
        }
        listaDevoluciones.add(devolucion);
        return devolucion;
    }

    // Actualiza una devolucion existente, validando que el ID exista primero
    public Devolucion actualizarDevolucion(Devolucion devolucion) {
        Devolucion existente = buscarPorIdDevolucion(devolucion.getIdDevolucion());
        if (existente == null) {
            throw new IllegalArgumentException("No se puede actualizar porque no existe devolución con ID " + devolucion.getIdDevolucion());
        }
        eliminarDevolucion(devolucion.getIdDevolucion());
        listaDevoluciones.add(devolucion);
        return devolucion;
    }

    // Elimina una devolucion por su ID, validando que exista
    public void eliminarDevolucion(int idDevolucion) {
        Devolucion devolucionEncontrada = buscarPorIdDevolucion(idDevolucion);
        if (devolucionEncontrada == null) {
            throw new IllegalArgumentException("No se puede eliminar porque no existe devolución con ID " + idDevolucion);
        }
        listaDevoluciones.remove(devolucionEncontrada);
    }
}
