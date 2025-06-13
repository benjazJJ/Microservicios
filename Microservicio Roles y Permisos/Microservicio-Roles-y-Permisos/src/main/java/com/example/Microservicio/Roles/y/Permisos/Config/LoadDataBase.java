package com.example.Microservicio.Roles.y.Permisos.Config;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.Microservicio.Roles.y.Permisos.Model.Permisos;
import com.example.Microservicio.Roles.y.Permisos.Model.Rol;
import com.example.Microservicio.Roles.y.Permisos.Repository.PermisoRepository;
import com.example.Microservicio.Roles.y.Permisos.Repository.RolRepository;

@Configuration
public class LoadDataBase {
    @Bean
    CommandLineRunner initDatabase(RolRepository rolRepo, PermisoRepository permisoRepo) {
        return args -> {
            if (rolRepo.count() == 0 && permisoRepo.count() == 0) {

                // Crear roles por defecto
                Rol admin = new Rol(null, "ADMIN", null);
                Rol editor = new Rol(null, "EDITOR", null);
                Rol lector = new Rol(null, "LECTOR", null);

                rolRepo.saveAll(Arrays.asList(admin, editor, lector));

                // Crear permisos por defecto
                Permisos p1 = new Permisos(null, "GESTIONAR_USUARIOS", admin);
                Permisos p2 = new Permisos(null, "CREAR_PUBLICACIONES", editor);
                Permisos p3 = new Permisos(null, "VER_CATALOGO", lector);

                permisoRepo.saveAll(Arrays.asList(p1, p2, p3));

                System.out.println("✅ Datos iniciales cargados correctamente.");
            } else {
                System.out.println("ℹ️ Las tablas ya contienen datos. No se cargaron nuevos registros.");
            }
        };
    }
}
