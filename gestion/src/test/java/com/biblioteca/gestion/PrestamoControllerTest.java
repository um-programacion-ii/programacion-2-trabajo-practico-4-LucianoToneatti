package com.biblioteca.gestion;

import com.biblioteca.gestion.controllers.PrestamoController;
import com.biblioteca.gestion.models.Prestamo;
import com.biblioteca.gestion.services.PrestamoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PrestamoControllerTest {
    @InjectMocks
    private PrestamoController prestamoController;

    @Mock
    private PrestamoService prestamoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testObtenerTodos() {
        Prestamo prestamo1 = new Prestamo(); // Asigna propiedades necesarias
        Prestamo prestamo2 = new Prestamo(); // Asigna propiedades necesarias
        List<Prestamo> prestamos = List.of(prestamo1, prestamo2);
        when(prestamoService.obtenerTodos()).thenReturn(prestamos);
        List<Prestamo> resultado = prestamoController.obtenerTodos();
        assertEquals(2, resultado.size());
        verify(prestamoService, times(1)).obtenerTodos();
    }

    @Test
    public void testObtenerPorId() {
        Long id = 1L;
        Prestamo prestamo = new Prestamo(); // Asigna propiedades necesarias
        when(prestamoService.buscarPorId(id)).thenReturn(prestamo);
        Prestamo resultado = prestamoController.obtenerPorId(id);
        assertNotNull(resultado);
        verify(prestamoService, times(1)).buscarPorId(id);
    }

    @Test
    public void testCrear() {
        Prestamo prestamo = new Prestamo(); // Asigna propiedades necesarias
        when(prestamoService.guardar(prestamo)).thenReturn(prestamo);
        Prestamo resultado = prestamoController.crear(prestamo);
        assertNotNull(resultado);
        verify(prestamoService, times(1)).guardar(prestamo);
    }

    @Test
    public void testActualizar() {
        Long id = 1L;
        Prestamo prestamo = new Prestamo(); // Asigna propiedades necesarias
        when(prestamoService.actualizar(id, prestamo)).thenReturn(prestamo);
        Prestamo resultado = prestamoController.actualizar(id, prestamo);
        assertNotNull(resultado);
        verify(prestamoService, times(1)).actualizar(id, prestamo);
    }

    @Test
    public void testEliminar() {
        Long id = 1L;
        prestamoController.eliminar(id);
        verify(prestamoService, times(1)).eliminar(id);
    }
}

