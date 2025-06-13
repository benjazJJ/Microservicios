package com.example.Microservicio.Roles.y.Permisos.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Microservicio.Roles.y.Permisos.Model.Rol;
import com.example.Microservicio.Roles.y.Permisos.Repository.RolRepository;


@Service
public class RolService {
    @Autowired
    private RolRepository rolRepository;

    // metodo para obtener todos los roles
    public List<Rol> obtenerTodosLosRoles() {
        return rolRepository.findAll();
    }

    // obtener un rol por id
    public Optional<Rol> obtenerPorId(Long id) {
        return rolRepository.findById(id);
    }

    // Metodo para crear un rol
    public Rol crearRol(Rol rol) {
        if (rolRepository.existsByNombreRol(rol.getNombreRol())) {
            throw new IllegalArgumentException("El rol ya existe");
        }
        return rolRepository.save(rol);
    }

    // Metodo para actualizar un rol
    public Rol actualizarRol(Long id, Rol rol) {
        Optional<Rol> existente = rolRepository.findById(id);
        if (existente.isPresent()) {
            rol.setId(id);
            return rolRepository.save(rol);
        }
        throw new IllegalArgumentException("Rol no encontrado");
    }

    // Metodo para eliminar un rol
    public void eliminarRol(Long id) {
        if (!rolRepository.existsById(id)) {
            throw new IllegalArgumentException("No se puede eliminar: el rol no existe");
        }
        rolRepository.deleteById(id);
    }
}
