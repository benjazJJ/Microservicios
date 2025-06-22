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

    /**
     * GET /api/v1/recomendaciones
     * Retorna todas las recomendaciones existentes.
     */
    @Test
    void obtenerTodas_deberiaRetornarOKYListaJson() throws Exception {
        List<Recomendacion> lista = List.of(
            new Recomendacion(1, "1984", "George Orwell", "Ficción", "Gran libro")
        );

        when(service.obtenerTodas()).thenReturn(lista);

        mockMvc.perform(get("/api/v1/recomendaciones"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.recomendacionList[0].id").value(1))
                .andExpect(jsonPath("$._embedded.recomendacionList[0].tituloLibro").value("1984"))
                .andExpect(jsonPath("$._embedded.recomendacionList[0]._links.self.href").exists());
    }

    /**
     * GET /api/v1/recomendaciones/2
     * Retorna una recomendación por ID si existe.
     */
    @Test
    void obtenerPorId_deberiaRetornarOKSiExiste() throws Exception {
        Recomendacion r = new Recomendacion(2, "Clean Code", "Robert C. Martin", "Programación", "Lectura obligada");
        when(service.obtenerPorId(2)).thenReturn(Optional.of(r));

        mockMvc.perform(get("/api/v1/recomendaciones/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.autor").value("Robert C. Martin"))
                .andExpect(jsonPath("$._links.self.href").exists())
                .andExpect(jsonPath("$._links.todos.href").exists())
                .andExpect(jsonPath("$._links.eliminar.href").exists());
    }

    /**
     * GET /api/v1/recomendaciones/999
     * Retorna 404 si no existe la recomendación.
     */
    @Test
    void obtenerPorId_deberiaRetornar404SiNoExiste() throws Exception {
        when(service.obtenerPorId(999)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/recomendaciones/999"))
                .andExpect(status().isNotFound());
    }

    /**
     * GET /api/v1/recomendaciones/categoria/Infantil
     * Retorna lista filtrada por categoría.
     */
    @Test
    void obtenerPorCategoria_deberiaRetornarOKYFiltrado() throws Exception {
        List<Recomendacion> lista = List.of(
            new Recomendacion(3, "El Principito", "Saint-Exupéry", "Infantil", "Para reflexionar")
        );

        when(service.obtenerPorCategoria("Infantil")).thenReturn(lista);

        mockMvc.perform(get("/api/v1/recomendaciones/categoria/Infantil"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.recomendacionList[0].categoria").value("Infantil"))
                .andExpect(jsonPath("$._embedded.recomendacionList[0].tituloLibro").value("El Principito"))
                .andExpect(jsonPath("$._embedded.recomendacionList[0]._links.self.href").exists());
    }
}
