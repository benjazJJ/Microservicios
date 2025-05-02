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

    public Autenticacion saveLogin(Autenticacion login) {
        return loginRepository.save(login);
    }

    public List<Autenticacion> getLogins() {
        return loginRepository.findAll();
    }
}
