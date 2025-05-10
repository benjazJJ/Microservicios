package Servicio.Microservicio.de.Administracion.de.Contenidos.y.Multimedia4.Service;

import Servicio.Microservicio.de.Administracion.de.Contenidos.y.Multimedia4.Model.Contenido;
import Servicio.Microservicio.de.Administracion.de.Contenidos.y.Multimedia4.Repository.ContenidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContenidoService {

    @Autowired
    private ContenidoRepository contenidoRepository;

    // Obtener todos los contenidos
    public List<Contenido> getContenidos() {
        return contenidoRepository.findAll();
    }

    // Guardar un nuevo contenido
    public Contenido saveContenido(Contenido contenido) {
        return contenidoRepository.save(contenido);
    }

    // Buscar contenido por ID
    public Contenido getContenidoId(int idContenido) {
        Optional<Contenido> contenido = contenidoRepository.findById(idContenido);
        return contenido.orElse(null); // Devuelve null si no existe
    }

    // Actualizar contenido (usa save, si existe actualiza, si no, crea uno nuevo)
    public Contenido updateContenido(Contenido contenido) {
        return contenidoRepository.save(contenido);
    }

    // Eliminar contenido por ID
    public String deleteContenido(int idContenido) {
        if (contenidoRepository.existsById(idContenido)) {
            contenidoRepository.deleteById(idContenido);
            return "Contenido eliminado correctamente";
        } else {
            return "Contenido no encontrado";
        }
    }
}

