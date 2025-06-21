package Servicio.Microservicio.Recomendacion.Lectura.Controller;

import Servicio.Microservicio.Recomendacion.Lectura.controller.RecomendacionController;
import Servicio.Microservicio.Recomendacion.Lectura.model.Recomendacion;
import Servicio.Microservicio.Recomendacion.Lectura.service.RecomendacionService;

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

@WebMvcTest(RecomendacionController.class)
public class RecomendacionControllerTest {

    @MockBean
    private RecomendacionService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void obtenerTodas_deberiaRetornarOKYListaJson() {
        List<Recomendacion> lista = Arrays.asList(
                new Recomendacion(1, "1984", "George Orwell", "Ficción", "Gran libro")
        );

        when(service.obtenerTodas()).thenReturn(lista);

        try {
            mockMvc.perform(get("/api/v1/recomendaciones"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].id").value(1))
                    .andExpect(jsonPath("$[0].tituloLibro").value("1984"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void obtenerPorId_deberiaRetornarOKSiExiste() {
        Recomendacion r = new Recomendacion(2, "Clean Code", "Robert C. Martin", "Programación", "Lectura obligada");

        when(service.obtenerPorId(2)).thenReturn(Optional.of(r));

        try {
            mockMvc.perform(get("/api/v1/recomendaciones/2"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(2))
                    .andExpect(jsonPath("$.autor").value("Robert C. Martin"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void obtenerPorId_deberiaRetornar404SiNoExiste() {
        when(service.obtenerPorId(999)).thenReturn(Optional.empty());

        try {
            mockMvc.perform(get("/api/v1/recomendaciones/999"))
                    .andExpect(status().isNotFound());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void obtenerPorCategoria_deberiaRetornarOKYFiltrado() {
        List<Recomendacion> lista = Arrays.asList(
                new Recomendacion(3, "El Principito", "Saint-Exupéry", "Infantil", "Para reflexionar")
        );

        when(service.obtenerPorCategoria("Infantil")).thenReturn(lista);

        try {
            mockMvc.perform(get("/api/v1/recomendaciones/categoria/Infantil"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].categoria").value("Infantil"))
                    .andExpect(jsonPath("$[0].tituloLibro").value("El Principito"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
