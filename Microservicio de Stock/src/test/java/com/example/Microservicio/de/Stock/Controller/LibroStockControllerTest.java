package com.example.Microservicio.de.Stock.Controller;

import com.example.Microservicio.de.Stock.Model.Categoria;
import com.example.Microservicio.de.Stock.Model.EstadoLibro;
import com.example.Microservicio.de.Stock.Model.LibroStock;
import com.example.Microservicio.de.Stock.Repository.EstadoLibroRepository;
import com.example.Microservicio.de.Stock.Repository.LibroStockRepository;
import com.example.Microservicio.de.Stock.Service.LibroStockService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LibroStockController.class)
public class LibroStockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LibroStockRepository libroStockRepository;

    @MockBean
    private EstadoLibroRepository estadoLibroRepository;

    @MockBean
    private LibroStockService libroStockService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void obtenerTodos_deberiaRetornarOKYListaJson() {
        Categoria categoria = new Categoria(1L, "Novela");
        EstadoLibro estado = new EstadoLibro(1L, "Disponible");
        List<LibroStock> lista = List.of(
                new LibroStock(1L, "El Quijote", "A1", "2", 5, categoria, estado)
        );

        when(libroStockRepository.findAll()).thenReturn(lista);

        try {
            mockMvc.perform(get("/api/v1/librostock"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$._embedded.libroStockList[0].nombreLibro").value("El Quijote"))
                    .andExpect(jsonPath("$._embedded.libroStockList[0]._links.self.href").exists())
                    .andExpect(jsonPath("$._embedded.libroStockList[0].estante").value("A1"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void obtenerPorId_existente_deberiaRetornarOK() {
        LibroStock libro = new LibroStock(1L, "1984", "D1", "4", 7, new Categoria(), new EstadoLibro());
        when(libroStockRepository.findById(1L)).thenReturn(Optional.of(libro));

        try {
            mockMvc.perform(get("/api/v1/librostock/1"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.nombreLibro").value("1984"))
                    .andExpect(jsonPath("$._links.self.href").exists())
                    .andExpect(jsonPath("$._links.todos.href").exists());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void obtenerPorId_noExiste_deberiaRetornar404() {
        when(libroStockRepository.findById(99L)).thenReturn(Optional.empty());

        try {
            mockMvc.perform(get("/api/v1/librostock/99"))
                    .andExpect(status().isNotFound());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
