package Servicio.Microservicio.de.Administracion.de.Contenidos.y.Multimedia4.Repository;

import Servicio.Microservicio.de.Administracion.de.Contenidos.y.Multimedia4.Model.Multimedia;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MultimediaRepository {

    private List<Multimedia> listaMultimedia = new ArrayList<>();

    public MultimediaRepository() {
        listaMultimedia.add(new Multimedia(1, "Nuevas adquisiciones", "Llegaron nuevos libros de tecnología", "http://localhost:8080/files/nuevos_libros.png", null, new java.sql.Date(System.currentTimeMillis()), null, "evento"));
        listaMultimedia.add(new Multimedia(2, "Oferta de verano", "Descuento en libros de literatura", "http://localhost:8080/files/oferta_verano.png", null, new java.sql.Date(System.currentTimeMillis()), null, "promoción"));
    }

    // Devuelve todas las publicaciones
    public List<Multimedia> obtenerMultimedias() {
        return new ArrayList<>(listaMultimedia);
    }

    // Busca una publicación por su ID
    public Multimedia buscarPorId(int idMultimedia) {
        for (Multimedia multimedia : listaMultimedia) {
            if (multimedia.getIdMultimedia() == idMultimedia) {
                return multimedia;
            }
        }
        return null;
    }

    // Guarda una nueva publicación, validando que no exista ID duplicado
    public Multimedia guardar(Multimedia multimedia) {
        if (buscarPorId(multimedia.getIdMultimedia()) != null) {
            throw new IllegalArgumentException("Ya existe una publicación con el ID " + multimedia.getIdMultimedia());
        }
        listaMultimedia.add(multimedia);
        return multimedia;
    }

    // Actualiza una publicación existente, validando primero que exista
    public Multimedia actualizar(Multimedia multimedia) {
        Multimedia existente = buscarPorId(multimedia.getIdMultimedia());
        if (existente == null) {
            throw new IllegalArgumentException("No se puede actualizar porque no existe publicación con ID " + multimedia.getIdMultimedia());
        }
        eliminar(multimedia.getIdMultimedia());
        listaMultimedia.add(multimedia);
        return multimedia;
    }

    // Elimina una publicación por su ID, validando que exista
    public void eliminar(int idMultimedia) {
        Multimedia multimediaEncontrada = buscarPorId(idMultimedia);
        if (multimediaEncontrada == null) {
            throw new IllegalArgumentException("No se puede eliminar porque no existe publicación con ID " + idMultimedia);
        }
        listaMultimedia.remove(multimediaEncontrada);
    }
}
