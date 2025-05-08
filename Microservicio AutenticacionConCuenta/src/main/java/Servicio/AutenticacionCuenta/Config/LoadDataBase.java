package Servicio.AutenticacionCuenta.Config;

import Servicio.AutenticacionCuenta.model.Usuario;
import Servicio.AutenticacionCuenta.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDataBase {

    @Bean
    CommandLineRunner initDatabase(UsuarioRepository userRepo) {
        return args -> {
            if (userRepo.count() == 0) {
                userRepo.save(new Usuario(0, "benja@correo.com", "1234"));
                userRepo.save(new Usuario(0, "admin@correo.com", "admin"));
                System.out.println(">> Usuarios iniciales cargados en la base de datos.");
            } else {
                System.out.println(">> Estos usuarios ya existen en la base de datos.");
                System.out.println(">> No pudieron añadir");
            }
        };
    }
}
