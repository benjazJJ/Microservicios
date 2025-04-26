package Servicio.Microservicio.de.Administracion.de.Contenidos.y.Multimedia4.Service;

import Servicio.Microservicio.de.Administracion.de.Contenidos.y.Multimedia4.Model.Multimedia;
import Servicio.Microservicio.de.Administracion.de.Contenidos.y.Multimedia4.Repository.MultimediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MultimediaService {

    @Autowired
    private MultimediaRepository multimediaRepository;

    // Devuelve todas las publicaciones multimedia
    public List<Multimedia> getMultimedias() {
        return multimediaRepository.obtenerMultimedias();
    }

    // Guarda una nueva publicación
    public Multimedia saveMultimedia(Multimedia multimedia) {
        return multimediaRepository.guardar(multimedia);
    }

    // Busca una publicación por su ID
    public Multimedia getMultimediaId(int idMultimedia) {
        return multimediaRepository.buscarPorId(idMultimedia);
    }

    // Actualiza una publicación existente
    public Multimedia updateMultimedia(Multimedia multimedia) {
        return multimediaRepository.actualizar(multimedia);
    }

    // Elimina una publicación por su ID
    public String deleteMultimedia(int idMultimedia) {
        multimediaRepository.eliminar(idMultimedia);
        return "Publicación eliminada correctamente";
    }
}
