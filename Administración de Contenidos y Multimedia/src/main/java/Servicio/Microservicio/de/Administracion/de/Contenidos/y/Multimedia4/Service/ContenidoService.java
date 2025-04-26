package Servicio.Microservicio.de.Administracion.de.Contenidos.y.Multimedia4.Service;

import Servicio.Microservicio.de.Administracion.de.Contenidos.y.Multimedia4.Model.Contenido;
import Servicio.Microservicio.de.Administracion.de.Contenidos.y.Multimedia4.Repository.ContenidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContenidoService {

    @Autowired
    private ContenidoRepository contenidoRepository;

    // Devuelve todos los contenidos
    public List<Contenido> getContenidos() {
        return contenidoRepository.obtenerContenidos();
    }

    // Guarda un nuevo contenido
    public Contenido saveContenido(Contenido contenido) {
        return contenidoRepository.guardar(contenido);
    }

    // Busca un contenido por su ID
    public Contenido getContenidoId(int idContenido) {
        return contenidoRepository.buscarPorId(idContenido);
    }

    // Actualiza un contenido existente
    public Contenido updateContenido(Contenido contenido) {
        return contenidoRepository.actualizar(contenido);
    }

    // Elimina un contenido por su ID
    public String deleteContenido(int idContenido) {
        contenidoRepository.eliminar(idContenido);
        return "Contenido eliminado correctamente";
    }
}
