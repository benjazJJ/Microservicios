package Servicio.Microservicio.de.Autenticacion9.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Servicio.Microservicio.de.Autenticacion9.Model.Login;
import Servicio.Microservicio.de.Autenticacion9.Repository.LoginRepository;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    // Devuelve todos los Usuarios
    public List<Login> getLogins() {
        return loginRepository.obtenerLogins();
    }

    // Guarda un nuevo usuario
    public Login saveLogin(Login login) {
        return loginRepository.guardarLogin(login);
    }

    // Busca un login por su ID
    public Login getLoginID(int idLogin) {
        return loginRepository.buscarPorIdLogin(idLogin);
    }

    // Actualiza un login existente
    public Login updateLogin(Login login) {
        return loginRepository.actualizarLogin(login);
    }

    // Elimina un login por su ID
    public String deleteLogin(int idLogin) {
        loginRepository.eliminarLogin(idLogin);
        return "Login eliminado correctamente";
    }

}
