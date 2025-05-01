package Servicio.Microservicio.de.Administracion.de.Contenidos.y.Multimedia4.Service;

import Servicio.Microservicio.de.Administracion.de.Contenidos.y.Multimedia4.Model.Multimedia;
import Servicio.Microservicio.de.Administracion.de.Contenidos.y.Multimedia4.Repository.MultimediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MultimediaService {

    @Autowired
    private MultimediaRepository multimediaRepository;

    // Obtener todas las publicaciones
    public List<Multimedia> getMultimedias() {
        return multimediaRepository.findAll();
    }

    // Guardar nueva publicación
    public Multimedia saveMultimedia(Multimedia multimedia) {
        return multimediaRepository.save(multimedia);
    }

    // Buscar publicación por ID
    public Multimedia getMultimediaId(int idMultimedia) {
        Optional<Multimedia> multimedia = multimediaRepository.findById(idMultimedia);
        return multimedia.orElse(null);
    }

    // Actualizar publicación
    public Multimedia updateMultimedia(Multimedia multimedia) {
        return multimediaRepository.save(multimedia);
    }

    // Eliminar publicación
    public String deleteMultimedia(int idMultimedia) {
        if (multimediaRepository.existsById(idMultimedia)) {
            multimediaRepository.deleteById(idMultimedia);
            return "Publicación eliminada correctamente";
        } else {
            return "Publicación no encontrada";
        }
    }
}

