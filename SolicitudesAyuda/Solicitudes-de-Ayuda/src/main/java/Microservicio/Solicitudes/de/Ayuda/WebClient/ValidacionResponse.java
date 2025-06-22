package Microservicio.Solicitudes.de.Ayuda.WebClient;

import lombok.Data;

@Data
public class ValidacionResponse {
    private boolean autenticado;
    private String rol;
}