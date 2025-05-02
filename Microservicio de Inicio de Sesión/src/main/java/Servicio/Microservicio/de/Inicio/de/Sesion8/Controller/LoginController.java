package Servicio.Microservicio.de.Inicio.de.Sesion8.Controller;

import Servicio.Microservicio.de.Inicio.de.Sesion8.Model.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController // Marca esta clase como un controlador REST de Spring
@RequestMapping("/api/v1/login") // Ruta base para todas las solicitudes relacionadas con login
public class LoginController {

    // URL del endpoint del microservicio de Autenticación al que se enviarán las credenciales
    private final String URL_AUTENTICACION = "http://localhost:8081/api/v1/autenticacion";

    // Se inyecta el RestTemplate definido en AppConfig para hacer peticiones HTTP
    @Autowired
    private RestTemplate restTemplate;

    // Endpoint que recibe las credenciales del usuario e intenta enviarlas al microservicio de Autenticación
    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        try {
            // Encabezados de la solicitud: se indica que se envía contenido tipo JSON
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // Se construye la entidad HTTP con las credenciales (body + headers)
            HttpEntity<LoginRequest> request = new HttpEntity<>(loginRequest, headers);

            // Se hace una petición POST al microservicio de Autenticación con los datos del login
            ResponseEntity<String> response = restTemplate.postForEntity(URL_AUTENTICACION, request, String.class);

            // Se responde con el mismo código de estado que haya entregado Autenticación
            return ResponseEntity.status(response.getStatusCode()).body("Inicio de sesión enviado a Autenticación");
        } catch (Exception e) {
            // Si hay un error, se devuelve estado 500 con el mensaje de la excepción
            return ResponseEntity.status(500).body("Error al enviar datos a Autenticación: " + e.getMessage());
        }
    }
}
