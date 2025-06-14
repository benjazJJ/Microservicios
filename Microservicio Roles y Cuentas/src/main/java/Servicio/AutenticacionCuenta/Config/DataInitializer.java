package main.java.Servicio.AutenticacionCuenta.Config;

import Servicio.AutenticacionCuenta.model.Rol;
import Servicio.AutenticacionCuenta.model.Usuario;
import Servicio.AutenticacionCuenta.repository.RolRepository;
import Servicio.AutenticacionCuenta.repository.UsuarioRepository;
import Servicio.AutenticacionCuenta.service.Encriptador;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostConstruct
    public void init() {
        // Crear roles si no existen
        crearRolSiNoExiste("ADMINISTRADOR");
        crearRolSiNoExiste("DOCENTE");
        crearRolSiNoExiste("ESTUDIANTE");
        crearRolSiNoExiste("BIBLIOTECARIO");

        // Precargar administradores
        crearUsuarioSiNoExiste("admin1@biblioteca.cl", "Admin Uno", "12345678-1", "956718092", "ADMINISTRADOR");
        crearUsuarioSiNoExiste("admin2@biblioteca.cl", "Admin Dos", "12345678-2", "941827010", "ADMINISTRADOR");

        // Precargar docentes
        crearUsuarioSiNoExiste("jose.morales@instituto.cl", "José Morales", "12345678-3", "912345678", "DOCENTE");
        crearUsuarioSiNoExiste("carla.espinoza@instituto.cl", "Carla Espinoza", "12345678-4", "912345679", "DOCENTE");
        crearUsuarioSiNoExiste("marco.diaz@instituto.cl", "Marco Díaz", "12345678-5", "912345680", "DOCENTE");
        crearUsuarioSiNoExiste("valentina.vera@instituto.cl", "Valentina Vera", "12345678-6", "912345681", "DOCENTE");
        crearUsuarioSiNoExiste("fernando.rios@instituto.cl", "Fernando Ríos", "12345678-7", "912345682", "DOCENTE");

        // Precargar bibliotecario
        crearUsuarioSiNoExiste("biblio@biblioteca.cl", "Bibliotecario", "12345678-8", "923456780", "BIBLIOTECARIO");
    }

    private void crearRolSiNoExiste(String nombre) {
        rolRepository.findByNombre(nombre).orElseGet(() -> rolRepository.save(new Rol(0, nombre)));
    }

    // Con este método se crea un usuario si no existe, asignando un rol específico
    private void crearUsuarioSiNoExiste(String correo, String nombre, String rut, String telefono, String nombreRol) {
        if (usuarioRepository.findByCorreo(correo).isEmpty() && usuarioRepository.findByRut(rut).isEmpty()) {
            Rol rol = rolRepository.findByNombre(nombreRol).orElseThrow();
            String passwordEncriptada = Encriptador.encriptar("123456"); // Contraseña por defecto
            Usuario nuevo = new Usuario();
            nuevo.setCorreo(correo);
            nuevo.setNombre(nombre);
            nuevo.setRut(rut);
            nuevo.setTelefono(telefono);
            nuevo.setContrasena(passwordEncriptada);
            nuevo.setRol(rol);
            usuarioRepository.save(nuevo);
        }
    }

}