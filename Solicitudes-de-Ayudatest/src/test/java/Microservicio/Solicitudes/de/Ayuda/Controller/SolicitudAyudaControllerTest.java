package Microservicio.Solicitudes.de.Ayuda.Controller;

import Microservicio.Solicitudes.de.Ayuda.Model.SolicitudAyuda;
import Microservicio.Solicitudes.de.Ayuda.Service.SolicitudAyudaService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SolicitudAyudaController.class)
public class SolicitudAyudaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SolicitudAyudaService service;

    @Test
    void obtenerTodas_retornaSolicitudes() throws Exception {
        SolicitudAyuda solicitud = new SolicitudAyuda(1, "user@correo.cl", "Consulta", "Necesito ayuda con mi préstamo", Date.valueOf("2025-06-21"));
        when(service.obtenerTodas()).thenReturn(List.of(solicitud));

        mockMvc.perform(get("/api/v1/ayuda"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idSolicitud").value(1))
                .andExpect(jsonPath("$[0].correoUsuario").value("user@correo.cl"));
    }

    @Test
    void obtenerPorId_retornaSolicitud() throws Exception {
        SolicitudAyuda solicitud = new SolicitudAyuda(2, "otro@correo.cl", "Problema", "Error en el sistema", Date.valueOf("2025-06-20"));
        when(service.obtenerPorId(2)).thenReturn(Optional.of(solicitud));

        mockMvc.perform(get("/api/v1/ayuda/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idSolicitud").value(2))
                .andExpect(jsonPath("$.asunto").value("Problema"));
    }

    @Test
    void obtenerPorId_noEncontrado() throws Exception {
        when(service.obtenerPorId(99)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/ayuda/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void obtenerPorCorreo_retornaListaFiltrada() throws Exception {
        SolicitudAyuda solicitud = new SolicitudAyuda(3, "filtrado@correo.cl", "Asunto", "Texto", Date.valueOf("2025-06-18"));
        when(service.obtenerPorCorreoUsuario("filtrado@correo.cl")).thenReturn(List.of(solicitud));

        mockMvc.perform(get("/api/v1/ayuda/usuario/filtrado@correo.cl"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].correoUsuario").value("filtrado@correo.cl"));
    }

    @Test
    void crearSolicitud_retornaCreada() throws Exception {
        SolicitudAyuda creada = new SolicitudAyuda(4, "nuevo@correo.cl", "Duda", "¿Cómo renovar el libro?", Date.valueOf("2025-06-21"));
        when(service.crear("Duda", "¿Cómo renovar el libro?", "nuevo@correo.cl", "1234")).thenReturn(creada);

        String json = """
            {
                "correo": "nuevo@correo.cl",
                "contrasena": "1234",
                "asunto": "Duda",
                "mensaje": "¿Cómo renovar el libro?"
            }
        """;

        mockMvc.perform(post("/api/v1/ayuda")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idSolicitud").value(4));
    }

    @Test
    void actualizarSolicitud_retornaActualizada() throws Exception {
        SolicitudAyuda actualizada = new SolicitudAyuda(5, "admin@correo.cl", "Actualizado", "Mensaje actualizado", Date.valueOf("2025-06-21"));
        when(service.actualizar(5, "Actualizado", "Mensaje actualizado", "admin@correo.cl", "1234")).thenReturn(actualizada);

        String json = """
            {
                "correo": "admin@correo.cl",
                "contrasena": "1234",
                "asunto": "Actualizado",
                "mensaje": "Mensaje actualizado"
            }
        """;

        mockMvc.perform(put("/api/v1/ayuda/5")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.asunto").value("Actualizado"));
    }

    @Test
    void eliminarSolicitud_retorna204() throws Exception {
        doNothing().when(service).eliminarPorId(6, "admin@correo.cl", "1234");

        String json = """
            {
                "correo": "admin@correo.cl",
                "contrasena": "1234"
            }
        """;

        mockMvc.perform(delete("/api/v1/ayuda/6")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isNoContent());
    }
}
