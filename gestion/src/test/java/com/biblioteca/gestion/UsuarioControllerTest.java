package com.biblioteca.gestion;

import com.biblioteca.gestion.controllers.UsuarioController;
import com.biblioteca.gestion.models.Usuario;
import com.biblioteca.gestion.services.UsuarioService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioController usuarioController;

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Juan Pérez");
        usuario.setEmail("juan@example.com");

        mockMvc = MockMvcBuilders.standaloneSetup(usuarioController).build();
    }

    @Test
    void cuandoObtenerTodosUsuariosEntoncesRetornaStatus200() throws Exception {
        List<Usuario> usuarios = Arrays.asList(usuario);
        when(usuarioService.obtenerTodos()).thenReturn(usuarios);

        mockMvc.perform(get("/api/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Juan Pérez"))
                .andExpect(jsonPath("$[0].email").value("juan@example.com"));
    }

    @Test
    void cuandoObtenerUsuarioPorIdEntoncesRetornaStatus200() throws Exception {
        when(usuarioService.buscarPorId(1L)).thenReturn(usuario);

        mockMvc.perform(get("/api/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Juan Pérez"))
                .andExpect(jsonPath("$.email").value("juan@example.com"));
    }

    @Test
    void cuandoCrearUsuarioEntoncesRetornaStatus201() throws Exception {
        when(usuarioService.guardar(any(Usuario.class))).thenReturn(usuario);

        mockMvc.perform(post("/api/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\": \"Juan Pérez\", \"email\": \"juan@example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Juan Pérez"))
                .andExpect(jsonPath("$.email").value("juan@example.com"));
    }

    @Test
    void cuandoActualizarUsuarioEntoncesRetornaStatus200() throws Exception {
        when(usuarioService.actualizar(eq(1L), any(Usuario.class))).thenReturn(usuario);

        mockMvc.perform(put("/api/usuarios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\": \"Juan Pérez\", \"email\": \"juan@example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Juan Pérez"))
                .andExpect(jsonPath("$.email").value("juan@example.com"));
    }

    @Test
    void cuandoEliminarUsuarioEntoncesRetornaStatus200() throws Exception {
        doNothing().when(usuarioService).eliminar(1L);

        mockMvc.perform(delete("/api/usuarios/1"))
                .andExpect(status().isOk());
    }
}

