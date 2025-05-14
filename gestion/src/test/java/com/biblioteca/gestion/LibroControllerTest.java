package com.biblioteca.gestion;

import com.biblioteca.gestion.controllers.LibroController;
import com.biblioteca.gestion.estados.EstadodeLibro;
import com.biblioteca.gestion.exceptions.GlobalException;
import com.biblioteca.gestion.exceptions.LibroNoEncontradoException;
import com.biblioteca.gestion.models.Libro;
import com.biblioteca.gestion.services.LibroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class LibroControllerTest {

    private MockMvc mockMvc;

    @Mock
    private LibroService libroService;

    @InjectMocks
    private LibroController libroController;

    private Libro libro;

    @BeforeEach
    void setUp() {
        libro = new Libro();
        libro.setId(1L);
        libro.setTitulo("1984");
        libro.setAutor("George Orwell");
        libro.setEstado(EstadodeLibro.DISPONIBLE);

        mockMvc = MockMvcBuilders.standaloneSetup(libroController)
                .setControllerAdvice(new GlobalException()) // Agrega el manejador de excepciones
                .build();
    }

    @Test
    void cuandoObtenerTodosLibrosEntoncesRetornaStatus200() throws Exception {
        List<Libro> libros = Arrays.asList(libro);
        when(libroService.obtenerTodos()).thenReturn(libros);

        mockMvc.perform(get("/api/libros"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].titulo").value("1984"))
                .andExpect(jsonPath("$[0].autor").value("George Orwell"))
                .andExpect(jsonPath("$[0].estado").value("DISPONIBLE"));
    }

    @Test
    void cuandoObtenerLibroPorIdEntoncesRetornaStatus200() throws Exception {
        when(libroService.buscarPorId(1L)).thenReturn(libro);

        mockMvc.perform(get("/api/libros/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("1984"))
                .andExpect(jsonPath("$.autor").value("George Orwell"))
                .andExpect(jsonPath("$.estado").value("DISPONIBLE"));
    }

    @Test
    void cuandoObtenerLibroPorIdNoExistenteEntoncesRetornaStatus404() throws Exception {
        when(libroService.buscarPorId(99L)).thenThrow(new LibroNoEncontradoException(99L));

        mockMvc.perform(get("/api/libros/99"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Libro no encontrado con ID: 99"));
    }

    @Test
    void cuandoCrearLibroEntoncesRetornaStatus201() throws Exception {
        when(libroService.guardar(any(Libro.class))).thenReturn(libro);

        mockMvc.perform(post("/api/libros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"titulo\": \"1984\", \"autor\": \"George Orwell\", \"estado\": \"DISPONIBLE\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.titulo").value("1984"))
                .andExpect(jsonPath("$.autor").value("George Orwell"))
                .andExpect(jsonPath("$.estado").value("DISPONIBLE"));
    }

    @Test
    void cuandoEliminarLibroEntoncesRetornaStatus200() throws Exception {
        doNothing().when(libroService).eliminar(1L);

        mockMvc.perform(delete("/api/libros/1"))
                .andExpect(status().isOk());
    }
}

