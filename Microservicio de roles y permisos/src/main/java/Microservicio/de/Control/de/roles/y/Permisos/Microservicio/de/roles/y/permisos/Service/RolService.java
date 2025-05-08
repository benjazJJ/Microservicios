package Microservicio.de.Control.de.roles.y.Permisos.Microservicio.de.roles.y.permisos.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Microservicio.de.Control.de.roles.y.Permisos.Microservicio.de.roles.y.permisos.Model.Rol;
import Microservicio.de.Control.de.roles.y.Permisos.Microservicio.de.roles.y.permisos.Repository.RoleRepository;

@Service
public class RolService {
    @Autowired
    private RoleRepository roleRepository;

    //metodo para obtener todos los roles
    public List<Rol> obtenerTodosLosRoles(){
        return roleRepository.findAll();
    }

    //obtener un rol por id
    public Optional<Rol> obtenerPorId(Long id){
        return roleRepository.findById(id);
    }

    //Metodo para crear un rol
    public Rol crearRol(Rol rol){
        if (roleRepository.existsByNombreRol(rol.getNombreRol())){
            throw new IllegalArgumentException("El rol ya existe");
        }
        return roleRepository.save(rol);
    }

    //Metodo para actualizar un rol
    public Rol actualizarRol(Long id, Rol rol){
        Optional<Rol> existente = roleRepository.findById(id);
        if(existente.isPresent()){
            rol.setId(id);
            return roleRepository.save(rol);
        }
        throw new IllegalArgumentException("Rol no encontrado");
    }

    //Metodo para eliminar un rol
    public void eliminarRol(Long id){
        if(!roleRepository.existsById(id)){
            throw new IllegalArgumentException("No se puede eliminar: el rol no existe");
        }
        roleRepository.deleteById(id);
    }
}
