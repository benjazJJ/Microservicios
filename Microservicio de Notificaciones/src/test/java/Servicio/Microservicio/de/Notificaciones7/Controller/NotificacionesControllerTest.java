package Servicio.Microservicio.de.Notificaciones7.Controller;

import Servicio.Microservicio.de.Notificaciones7.Model.Notificaciones;
import Servicio.Microservicio.de.Notificaciones7.Service.NotificacionesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NotificacionesController.class)
public class NotificacionesControllerTest {

    @MockBean
    private NotificacionesService notificacionesService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllNotificaciones_returnsOKAndJson() throws Exception {
        List<Notificaciones> lista = Arrays.asList(
                new Notificaciones(1, "Mensaje 1", "INFO", "receptor@correo.cl", "emisor@correo.cl")
        );

        when(notificacionesService.obtenerTodas()).thenReturn(lista);

        mockMvc.perform(get("/api/v1/notificaciones"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.notificacionesList[0].mensaje").value("Mensaje 1"))
                .andExpect(jsonPath("$._embedded.notificacionesList[0].correoReceptor").value("receptor@correo.cl"))
                .andExpect(jsonPath("$._embedded.notificacionesList[0]._links.self.href").exists());
    }

    @Test
    void getNotificacionPorId_returnsOK() throws Exception {
        Notificaciones noti = new Notificaciones(2, "Mensaje 2", "ALERTA", "receptor@correo.cl", "emisor@correo.cl");

        when(notificacionesService.obtenerPorId(2)).thenReturn(Optional.of(noti));

        mockMvc.perform(get("/api/v1/notificaciones/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mensaje").value("Mensaje 2"))
                .andExpect(jsonPath("$._links.self.href").exists())
                .andExpect(jsonPath("$._links.todas.href").exists())
                .andExpect(jsonPath("$._links.eliminar.href").exists());
    }

    @Test
    void getNotificacionesPorEmisor_returnsOK() throws Exception {
        List<Notificaciones> lista = Arrays.asList(
                new Notificaciones(3, "Mensaje desde emisor", "INFO", "destinatario@correo.cl", "admin@correo.cl")
        );

        when(notificacionesService.obtenerPorEmisor("admin@correo.cl")).thenReturn(lista);

        mockMvc.perform(get("/api/v1/notificaciones/emisor/admin@correo.cl"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.notificacionesList[0].correoEmisor").value("admin@correo.cl"))
                .andExpect(jsonPath("$._embedded.notificacionesList[0]._links.self.href").exists());
    }

    @Test
    void getNotificacionesPorReceptor_returnsOK() throws Exception {
        List<Notificaciones> lista = Arrays.asList(
                new Notificaciones(4, "Mensaje hacia receptor", "INFO", "cliente@correo.cl", "admin@correo.cl")
        );

        when(notificacionesService.obtenerPorReceptor("cliente@correo.cl")).thenReturn(lista);

        mockMvc.perform(get("/api/v1/notificaciones/receptor/cliente@correo.cl"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.notificacionesList[0].correoReceptor").value("cliente@correo.cl"))
                .andExpect(jsonPath("$._embedded.notificacionesList[0]._links.self.href").exists());
    }
}
