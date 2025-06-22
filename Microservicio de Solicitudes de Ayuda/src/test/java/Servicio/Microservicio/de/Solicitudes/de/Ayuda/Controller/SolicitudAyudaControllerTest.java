package Servicio.Microservicio.de.Solicitudes.de.Ayuda.Controller;

import com.ayuda.controller.SolicitudAyudaController;
import com.ayuda.model.SolicitudAyuda;
import com.ayuda.service.SolicitudAyudaService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(SolicitudAyudaController.class)
@ContextConfiguration(classes = com.ayuda.SolicitudAyudaApplication.class)
public class SolicitudAyudaControllerTest {

    @MockBean
    private SolicitudAyudaService service;

    @Autowired
    private MockMvc mockMvc;

    /**
     * Test para GET /api/v1/ayuda
     * Verifica que se devuelva una lista de solicitudes con estado 200 y contenido JSON.
     */
    @Test
    void obtenerTodas_deberiaRetornarOKYListaJson() throws Exception {
        List<SolicitudAyuda> lista = List.of(
                new SolicitudAyuda(1, "alumno@correo.cl", "Acceso", "No puedo ingresar", Date.valueOf("2025-06-10"))
        );

        when(service.obtenerTodas()).thenReturn(lista);

        mockMvc.perform(get("/api/v1/ayuda"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.solicitudAyudaList[0].correoUsuario").value("alumno@correo.cl"))
                .andExpect(jsonPath("$._embedded.solicitudAyudaList[0].asunto").value("Acceso"))
                .andExpect(jsonPath("$._embedded.solicitudAyudaList[0]._links.self.href").exists());
    }

    /**
     * Test para GET /api/v1/ayuda/2
     * Verifica que se devuelva una solicitud específica con sus datos y enlaces HATEOAS.
     */
    @Test
    void obtenerPorId_existente_deberiaRetornarOK() throws Exception {
        SolicitudAyuda solicitud = new SolicitudAyuda(
                2, "usuario@correo.cl", "Consulta", "¿Dónde renovar?", Date.valueOf("2025-06-11")
        );

        when(service.obtenerPorId(2)).thenReturn(Optional.of(solicitud));

        mockMvc.perform(get("/api/v1/ayuda/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idSolicitud").value(2))
                .andExpect(jsonPath("$.correoUsuario").value("usuario@correo.cl"))
                .andExpect(jsonPath("$.asunto").value("Consulta"))
                .andExpect(jsonPath("$._links.self.href").exists())
                .andExpect(jsonPath("$._links.todos.href").exists())
                .andExpect(jsonPath("$._links.eliminar.href").exists());
    }

    /**
     * Test para GET /api/v1/ayuda/99
     * Verifica que si la solicitud no existe, se responde con 404.
     */
    @Test
    void obtenerPorId_inexistente_deberiaRetornar404() throws Exception {
        when(service.obtenerPorId(99)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/ayuda/99"))
                .andExpect(status().isNotFound());
    }

    /**
     * Test para GET /api/v1/ayuda/usuario/docente@correo.cl
     * Verifica que se obtengan solicitudes filtradas por correo del usuario.
     */
    @Test
    void obtenerPorCorreo_deberiaRetornarOKYJson() throws Exception {
        List<SolicitudAyuda> lista = List.of(
                new SolicitudAyuda(3, "docente@correo.cl", "Error", "Sistema caído", Date.valueOf("2025-06-12"))
        );

        when(service.obtenerPorCorreoUsuario("docente@correo.cl")).thenReturn(lista);

        mockMvc.perform(get("/api/v1/ayuda/usuario/docente@correo.cl"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.solicitudAyudaList[0].correoUsuario").value("docente@correo.cl"))
                .andExpect(jsonPath("$._embedded.solicitudAyudaList[0].asunto").value("Error"))
                .andExpect(jsonPath("$._embedded.solicitudAyudaList[0]._links.self.href").exists());
    }
}
