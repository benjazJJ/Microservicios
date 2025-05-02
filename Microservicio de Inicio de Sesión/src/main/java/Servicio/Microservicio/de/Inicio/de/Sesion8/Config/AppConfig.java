package Servicio.Microservicio.de.Inicio.de.Sesion8.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration // Indica que esta clase contiene configuraciones de Spring
public class AppConfig {

    // Define un bean de tipo RestTemplate para que esté disponible en todo el contexto de Spring
    @Bean
    public RestTemplate restTemplate() {
        // RestTemplate permite hacer llamadas HTTP desde este microservicio a otros (como autenticación)
        return new RestTemplate();
    }
}
