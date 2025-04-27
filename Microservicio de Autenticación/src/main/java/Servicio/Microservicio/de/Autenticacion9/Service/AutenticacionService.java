package Servicio.Microservicio.de.Autenticacion9.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Servicio.Microservicio.de.Autenticacion9.Model.Autenticacion;
import Servicio.Microservicio.de.Autenticacion9.Repository.AutenticacionRepository;

@Service
public class AutenticacionService {

    @Autowired
    private AutenticacionRepository loginRepository;

    // Devuelve todos los Usuarios
    public List<Autenticacion> getLogins() {
        return loginRepository.obtenerLogins();
    }

    // Guarda un nuevo usuario
    public Autenticacion saveLogin(Autenticacion login) {
        return loginRepository.guardarLogin(login);
    }

    // Busca un login por su ID
    public Autenticacion getLoginID(int idLogin) {
        return loginRepository.buscarPorIdLogin(idLogin);
    }

    // Actualiza un login existente
    public Autenticacion updateLogin(Autenticacion login) {
        return loginRepository.actualizarLogin(login);
    }

    // Elimina un login por su ID
    public String deleteLogin(int idLogin) {
        loginRepository.eliminarLogin(idLogin);
        return "Login eliminado correctamente";
    }

}
