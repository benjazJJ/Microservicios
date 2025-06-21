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

/**
 * Test unitarios para el controlador LibroStockController,
 * usando MockMvc para simular peticiones HTTP.
 */
@WebMvcTest(LibroStockController.class) // Carga solo el contexto del controlador que se quiere testear
public class LibroStockControllerTest {

    @Autowired
    private MockMvc mockMvc; // Herramienta para simular peticiones HTTP y validar respuestas

    @MockBean
    private LibroStockRepository libroStockRepository; // Simula el comportamiento del repositorio de libros

    @MockBean
    private EstadoLibroRepository estadoLibroRepository; // Simula el comportamiento del repositorio de estados (no se usa aquí, pero es requerido por el controlador)

    @MockBean
    private LibroStockService libroStockService; // Servicio mockeado para evitar lógica real

    private final ObjectMapper objectMapper = new ObjectMapper(); // Conversor de objetos a JSON y viceversa (no se usa aquí directamente)

    /**
     * Test para GET /api/v1/librostock
     * Verifica que se devuelva la lista de libros correctamente y con enlaces HATEOAS.
     */
    @Test
    void obtenerTodos_deberiaRetornarOKYListaJson() {
        Categoria categoria = new Categoria(1L, "Novela");
        EstadoLibro estado = new EstadoLibro(1L, "Disponible");
        List<LibroStock> lista = List.of(
                new LibroStock(1L, "El Quijote", "A1", "2", 5, categoria, estado)
        );

        // Simula que al llamar a findAll() se devuelva la lista anterior
        when(libroStockRepository.findAll()).thenReturn(lista);

        try {
            // Realiza la petición GET y valida:
            mockMvc.perform(get("/api/v1/librostock"))
                    .andExpect(status().isOk()) // Código 200
                    .andExpect(jsonPath("$._embedded.libroStockList[0].nombreLibro").value("El Quijote")) // Valida nombre del primer libro
                    .andExpect(jsonPath("$._embedded.libroStockList[0]._links.self.href").exists()) // Verifica que HATEOAS incluya link a sí mismo
                    .andExpect(jsonPath("$._embedded.libroStockList[0].estante").value("A1")); // Valida campo "estante"
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Test para GET /api/v1/librostock/1
     * Verifica que se devuelva el libro con ID 1, con enlaces HATEOAS incluidos.
     */
    @Test
    void obtenerPorId_existente_deberiaRetornarOK() {
        LibroStock libro = new LibroStock(1L, "1984", "D1", "4", 7, new Categoria(), new EstadoLibro());

        // Simula que al buscar el ID 1 se encuentra el libro "1984"
        when(libroStockRepository.findById(1L)).thenReturn(Optional.of(libro));

        try {
            mockMvc.perform(get("/api/v1/librostock/1"))
                    .andExpect(status().isOk()) // Código 200
                    .andExpect(jsonPath("$.nombreLibro").value("1984")) // Valida el nombre del libro
                    .andExpect(jsonPath("$._links.self.href").exists()) // HATEOAS: enlace a sí mismo
                    .andExpect(jsonPath("$._links.todos.href").exists()); // HATEOAS: enlace a la lista general
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Test para GET /api/v1/librostock/99
     * Verifica que si el libro no existe, el endpoint responde con 404.
     */
    @Test
    void obtenerPorId_noExiste_deberiaRetornar404() {
        // Simula que al buscar el ID 99 no se encuentra ningún libro
        when(libroStockRepository.findById(99L)).thenReturn(Optional.empty());

        try {
            mockMvc.perform(get("/api/v1/librostock/99"))
                    .andExpect(status().isNotFound()); // Código 404 esperado
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
