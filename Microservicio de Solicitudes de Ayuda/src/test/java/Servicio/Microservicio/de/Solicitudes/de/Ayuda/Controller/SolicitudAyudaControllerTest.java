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

    @Test
    void obtenerTodas_deberiaRetornarOkYJson() throws Exception {
        List<SolicitudAyuda> lista = Arrays.asList(
            new SolicitudAyuda(1, "alumno@correo.cl", "Acceso", "No puedo ingresar", Date.valueOf("2025-06-10"))
        );

        when(service.obtenerTodas()).thenReturn(lista);

        mockMvc.perform(get("/api/v1/ayuda"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idSolicitud").value(1));
    }

    @Test
    void obtenerPorId_existente_deberiaRetornarOkYJson() throws Exception {
        SolicitudAyuda solicitud = new SolicitudAyuda(
            2, "usuario@correo.cl", "Consulta", "¿Dónde renovar?", Date.valueOf("2025-06-11")
        );

        when(service.obtenerPorId(2)).thenReturn(Optional.of(solicitud));

        mockMvc.perform(get("/api/v1/ayuda/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idSolicitud").value(2))
                .andExpect(jsonPath("$.correoUsuario").value("usuario@correo.cl"))
                .andExpect(jsonPath("$.asunto").value("Consulta"));
    }

    @Test
    void obtenerPorId_inexistente_deberiaRetornar404() throws Exception {
        when(service.obtenerPorId(99)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/ayuda/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void obtenerPorCorreo_deberiaRetornarOkYJson() throws Exception {
        List<SolicitudAyuda> lista = Arrays.asList(
            new SolicitudAyuda(3, "docente@correo.cl", "Error", "Sistema caído", Date.valueOf("2025-06-12"))
        );

        when(service.obtenerPorCorreoUsuario("docente@correo.cl")).thenReturn(lista);

        mockMvc.perform(get("/api/v1/ayuda/usuario/docente@correo.cl"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].correoUsuario").value("docente@correo.cl"));
    }
}
