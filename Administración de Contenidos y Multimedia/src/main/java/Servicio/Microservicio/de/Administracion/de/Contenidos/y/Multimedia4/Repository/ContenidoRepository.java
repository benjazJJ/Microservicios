package Servicio.Microservicio.de.Administracion.de.Contenidos.y.Multimedia4.Repository;

import Servicio.Microservicio.de.Administracion.de.Contenidos.y.Multimedia4.Model.Contenido;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ContenidoRepository {

    private List<Contenido> listaContenidos = new ArrayList<>();

    // Constructor: agrega algunos contenidos de ejemplo al iniciar
    public ContenidoRepository() {
        listaContenidos.add(new Contenido(1, "Reglamento_Biblioteca.pdf", "pdf", "http://localhost:8080/files/Reglamento_Biblioteca.pdf", new java.sql.Date(System.currentTimeMillis()), "Normas generales de uso de la biblioteca", "publico"));
        listaContenidos.add(new Contenido(2, "Manual_Usuario.pdf", "pdf", "http://localhost:8080/files/Manual_Usuario.pdf", new java.sql.Date(System.currentTimeMillis()), "Guía para el uso del catálogo en línea", "publico"));
    }

    // Devuelve todos los contenidos
public List<Contenido> obtenerContenidos() {
    return new ArrayList<>(listaContenidos); // Se devuelve una copia para proteger la lista original
}


    // Busca un contenido por su ID
    public Contenido buscarPorId(int idContenido) {
        for (Contenido contenido : listaContenidos) {
            if (contenido.getIdContenido() == idContenido) {
                return contenido;
            }
        }
        return null;
    }

    // Guarda un nuevo contenido, validando que no exista un ID duplicado
    public Contenido guardar(Contenido contenido) {
        if (buscarPorId(contenido.getIdContenido()) != null) {
            throw new IllegalArgumentException("Ya existe un contenido con el ID " + contenido.getIdContenido());
        }
        listaContenidos.add(contenido);
        return contenido;
    }

    // Actualiza un contenido existente, validando que el ID exista primero
    public Contenido actualizar(Contenido contenido) {
        Contenido existente = buscarPorId(contenido.getIdContenido());
        if (existente == null) {
            throw new IllegalArgumentException("No se puede actualizar porque no existe contenido con ID " + contenido.getIdContenido());
        }
        eliminar(contenido.getIdContenido());
        listaContenidos.add(contenido);
        return contenido;
    }

    // Elimina un contenido por su ID, validando que exista
    public void eliminar(int idContenido) {
        Contenido contenidoEncontrado = buscarPorId(idContenido);
        if (contenidoEncontrado == null) {
            throw new IllegalArgumentException("No se puede eliminar porque no existe contenido con ID " + idContenido);
        }
        listaContenidos.remove(contenidoEncontrado);
    }
}

