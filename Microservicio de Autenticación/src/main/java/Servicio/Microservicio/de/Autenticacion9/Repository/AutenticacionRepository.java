package Servicio.Microservicio.de.Autenticacion9.Repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import Servicio.Microservicio.de.Autenticacion9.Model.Autenticacion;

@Repository
public class AutenticacionRepository {

    private List<Autenticacion> listaLogin = new ArrayList<>();

    public AutenticacionRepository() {
        listaLogin.add(new Autenticacion(1, "carlos.munoz@example.com", "contrasena123"));
        listaLogin.add(new Autenticacion(2, "maria.lopez@example.com", "pass456"));
        
    }
    public List<Autenticacion> obtenerLogins() {
        return new ArrayList<>(listaLogin);
    }

    // Busca un Login por su ID
    public Autenticacion buscarPorIdLogin(int idLogin) {
        for (Autenticacion login : listaLogin) {
            if (login.getIdLogin() == idLogin) {
                return login;
            }
        }
        return null;
    }

    // Guarda un nuevo login, validando que no exista un ID duplicado
    public Autenticacion guardarLogin(Autenticacion login) {
        if (buscarPorIdLogin(login.getIdLogin()) != null) {
            throw new IllegalArgumentException("Ya existe un login con el ID " + login.getIdLogin());
        }
        listaLogin.add(login);
        return login;
    }

    // Actualiza un login existente, validando que el ID exista primero
    public Autenticacion actualizarLogin(Autenticacion login) {
        Autenticacion existentelogin = buscarPorIdLogin(login.getIdLogin());
        if (existentelogin == null) {
            throw new IllegalArgumentException("No se puede actualizar porque no existe un login con ID " + login.getIdLogin());
        }
        eliminarLogin(login.getIdLogin());
        listaLogin.add(login);
        return login;
    }

    // Elimina un login por su ID, validando que exista
    public void eliminarLogin(int idLogin) {
        Autenticacion loginEncontrado = buscarPorIdLogin(idLogin);
        if (loginEncontrado == null) {
            throw new IllegalArgumentException("No se puede eliminar porque no existe un login con ID " + idLogin);
        }
        listaLogin.remove(loginEncontrado);
    }
}
