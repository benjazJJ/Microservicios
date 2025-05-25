package Servicio.Microservicio.Prestamos.webclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class CuentasClient {

    private final WebClient webClient;

    public CuentasClient(@Value("${cuentas-service.url}") String baseUrl) {
        this.webClient = WebClient.builder().baseUrl(baseUrl).build();
    }

    public boolean validarUsuarioPorId(Integer idUsuario) {
        try {
            webClient.get()
                    .uri("/usuario/" + idUsuario)
                    .retrieve()
                    .bodyToMono(Void.class)
                    .block();
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Ya existe un usuario con el ID " + idUsuario);
        }
    }

    public boolean validarUsuarioPorRut(String rut) {
        try {
            webClient.get()
                    .uri("/usuario/rut/" + rut)
                    .retrieve()
                    .bodyToMono(Void.class)
                    .block();
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Ya existe un usuario con el RUT: " + rut);
        }
    }
}
