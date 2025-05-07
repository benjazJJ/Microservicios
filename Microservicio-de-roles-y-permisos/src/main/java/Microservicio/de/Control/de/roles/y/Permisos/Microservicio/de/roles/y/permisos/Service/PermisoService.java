package Microservicio.de.Control.de.roles.y.Permisos.Microservicio.de.roles.y.permisos.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Microservicio.de.Control.de.roles.y.Permisos.Microservicio.de.roles.y.permisos.Model.Permisos;
import Microservicio.de.Control.de.roles.y.Permisos.Microservicio.de.roles.y.permisos.Model.Rol;
import Microservicio.de.Control.de.roles.y.Permisos.Microservicio.de.roles.y.permisos.Repository.PermisoRepository;

@Service
public class PermisoService {

    @Autowired
    private PermisoRepository permisoRepository;
    
    //Listar todos los permisos
    public List<Permisos> obtenerPermisos() {
        return permisoRepository.findAll();
    }

    //buscar permiso por id
    public Optional<Permisos> obtenerPermisosPorId(long id){
        return permisoRepository.findById(id);
    }

    //buscar permiso por nomnbre
    public List<Permisos> obtenerPermisosPorNombre(String nombre){
        return permisoRepository.findByNombrePermiso(nombre);
    }

    //Buscar permiso por roles
    public List<Permisos> obtenerPermisosPorRol(Rol rol){
        return permisoRepository.findByRol(rol);
    }

    //Crear nuevo permiso
    public Permisos crearPermiso(Permisos permiso){
        return permisoRepository.save(permiso);
    }

    //Actualizar Permiso Existente
    public Permisos actualizarPermiso(Long id, Permisos permisoActualizado){
        Optional<Permisos> existente = permisoRepository.findById(id);
        if(existente.isPresent()){
            permisoActualizado.setId(id);
            return permisoRepository.save(permisoActualizado);
        }
        throw new IllegalArgumentException("Permiso no encontrado");
    }

    //Eliminar permiso
    public void eliminarPermiso(Long id){
        if(!permisoRepository.existsById(id)){
            throw new IllegalArgumentException("No se puede eliminar: Permiso no existente");
        }
        permisoRepository.deleteById(id);
    }
}
