package org.asm.labs.controller;

import lombok.SneakyThrows;
import org.asm.labs.model.Genre;
import org.asm.labs.service.GenreService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(GenreApiController.class)
class GenreApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GenreService genreService;

    @DisplayName("Должен вернуть статус код 200 на запрос получения всех жанров")
    @Test
    @SneakyThrows
    void shouldReturn200onGetAllGenres() {
        when(genreService.findAll()).thenReturn(Collections.singletonList(new Genre(0, "Genre MVC #Test")));
        mockMvc.perform(get("/api/v1/genres"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(0)))
                .andExpect(jsonPath("$[0].genreName", is("Genre MVC #Test")))
                .andDo(print());
        verify(genreService, times(1)).findAll();
        verifyNoMoreInteractions(genreService);

    }
}