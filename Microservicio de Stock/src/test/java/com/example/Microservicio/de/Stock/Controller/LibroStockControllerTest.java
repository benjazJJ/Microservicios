package com.example.Microservicio.de.Stock.Controller;

import com.example.Microservicio.de.Stock.Model.Categoria;
import com.example.Microservicio.de.Stock.Model.EstadoLibro;
import com.example.Microservicio.de.Stock.Model.LibroStock;
import com.example.Microservicio.de.Stock.Repository.EstadoLibroRepository;
import com.example.Microservicio.de.Stock.Repository.LibroStockRepository;
import com.example.Microservicio.de.Stock.Service.LibroStockService;
import com.example.Microservicio.de.Stock.Service.ValidacionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
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

    // Get
    @Test
    void obtenerTodos_deberiaRetornarOkYListaJson() throws Exception {
        Categoria categoria = new Categoria(1L, "Novela");
        EstadoLibro estado = new EstadoLibro(1L, "Disponible");

        List<LibroStock> lista = List.of(
                new LibroStock(1L, "El Quijote", "A1", "2", 5, categoria, estado));

        when(libroStockRepository.findAll()).thenReturn(lista);

        mockMvc.perform(get("/api/v1/librostock"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombreLibro").value("El Quijote"))
                .andExpect(jsonPath("$[0].cantidad").value(5));
    }

    @Test
    void obtenerPorId_existente_deberiaRetornarOkYJson() throws Exception {
        Categoria categoria = new Categoria(1L, "Historia");
        EstadoLibro estado = new EstadoLibro(2L, "Prestado");

        LibroStock libro = new LibroStock(2L, "Breve Historia de Chile", "B2", "1", 3, categoria, estado);

        when(libroStockRepository.findById(2L)).thenReturn(Optional.of(libro));

        mockMvc.perform(get("/api/v1/librostock/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreLibro").value("Breve Historia de Chile"))
                .andExpect(jsonPath("$.cantidad").value(3))
                .andExpect(jsonPath("$.estante").value("B2"));
    }

    @Test
    void obtenerPorId_inexistente_deberiaRetornar404() throws Exception {
        when(libroStockRepository.findById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/librostock/99"))
                .andExpect(status().isNotFound());
    }

    // Put
    @Test
    void actualizarCantidad_valida_deberiaRetornarOk() throws Exception {
        Categoria categoria = new Categoria(1L, "Ciencia");
        EstadoLibro estado = new EstadoLibro(1L, "Disponible");

        LibroStock libro = new LibroStock(3L, "Física Cuántica", "C3", "5", 6, categoria, estado);

        when(libroStockRepository.findById(3L)).thenReturn(Optional.of(libro));
        when(libroStockRepository.save(libro)).thenReturn(libro);

        String body = "{ \"cantidad\": 10 }";

        mockMvc.perform(put("/api/v1/librostock/actualizar-stock/3")
                .contentType("application/json")
                .content(body))
                .andExpect(status().isOk())
                .andExpect(content().string("Cantidad actualizada correctamente"));
    }

    @Test
    void actualizarCantidad_invalida_deberiaRetornar400() throws Exception {
        LibroStock libro = new LibroStock();
        when(libroStockRepository.findById(4L)).thenReturn(Optional.of(libro));

        String body = "{ \"cantidad\": -3 }";

        mockMvc.perform(put("/api/v1/librostock/actualizar-stock/4")
                .contentType("application/json")
                .content(body))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Cantidad inválida"));
    }

    @Test
    void actualizarCantidad_idNoExiste_deberiaRetornar404() throws Exception {
        when(libroStockRepository.findById(77L)).thenReturn(Optional.empty());

        String body = "{ \"cantidad\": 7 }";

        mockMvc.perform(put("/api/v1/librostock/actualizar-stock/77")
                .contentType("application/json")
                .content(body))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Libro no encontrado"));
    }

    @Test
    void crearLibroStock_nuevo_deberiaRetornarOk() throws Exception {
        Map<String, Object> datos = new HashMap<>();
        datos.put("correo", "admin@correo.cl");
        datos.put("contrasena", "1234");
        datos.put("nombreLibro", "Nuevo Libro");
        datos.put("estante", "C2");
        datos.put("fila", "3");
        datos.put("cantidad", 4);
        datos.put("idCategoria", 1L);
        datos.put("idEstado", 1L);

        LibroStock libro = new LibroStock(null, "Nuevo Libro", "C2", "3", 4, new Categoria(), new EstadoLibro());

        when(libroStockService.validarUsuario(anyString(), anyString()))
                .thenReturn(new ValidacionResponse(true, "ADMINISTRADOR"));
        when(libroStockService.mapToLibroStock(anyMap())).thenReturn(libro);
        when(libroStockRepository.findAll()).thenReturn(Collections.emptyList());
        when(libroStockRepository.save(libro)).thenReturn(libro);

        mockMvc.perform(post("/api/v1/librostock/crear")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(datos)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreLibro").value("Nuevo Libro"));
    }

    

}
