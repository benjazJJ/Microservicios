package Servicio.Microservicio.de.Autenticacion9.Service;

import Servicio.Microservicio.de.Autenticacion9.Model.Autenticacion;
import Servicio.Microservicio.de.Autenticacion9.Repository.AutenticacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutenticacionService {

    @Autowired
    private AutenticacionRepository loginRepository;

    // Obtener todos los logins
    public List<Autenticacion> getLogins() {
        return loginRepository.findAll();
    }

    // Guardar un nuevo login
    public Autenticacion saveLogin(Autenticacion login) {
        return loginRepository.save(login);
    }

    // Buscar login por ID
    public Autenticacion getLoginById(int idLogin) {
        return loginRepository.findById(idLogin).orElse(null);
    }

    // Validar correo y contraseña (para inicio de sesión)
    public boolean validarCredenciales(String correo, String contrasena) {
        return loginRepository.findByCorreoAndContrasena(correo, contrasena).isPresent();
    }

    // Actualizar login
    public Autenticacion updateLogin(Autenticacion login) {
        return loginRepository.save(login);
    }

    // Eliminar login por ID
    public String deleteLogin(int idLogin) {
        if (loginRepository.existsById(idLogin)) {
            loginRepository.deleteById(idLogin);
            return "Login eliminado correctamente";
        } else {
            return "Login no encontrado";
        }
    }
}
