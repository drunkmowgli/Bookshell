package org.asm.labs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.asm.labs.model.Author;
import org.asm.labs.service.AuthorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthorApiController.class)
class AuthorApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorService authorService;

    @DisplayName("Должен статус код 200, на запрос получения всех авторов")
    @Test
    @SneakyThrows
    void shouldReturn200onGetAllAuthors() {
        when(authorService.findAll()).thenReturn(Collections.singletonList(new Author("Author MVC #Test")));
        mockMvc.perform(get("/api/v1/authors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(0)))
                .andExpect(jsonPath("$[0].name", is("Author MVC #Test")))
                .andDo(print());
        verify(authorService, times(1)).findAll();
        verifyNoMoreInteractions(authorService);
    }

    @DisplayName("Должен добавить автора, затем вернуть JSON и статус isCreated (200)")
    @Test
    @SneakyThrows
    void shouldReturn200OnCreateAuthor() {
        String authorName = "Author MVC #Test";
        Author author = new Author(authorName);
        when(authorService.save(authorName)).thenReturn(author);
        ObjectMapper objectMapper = new ObjectMapper();
        String authorJsonString = objectMapper.writeValueAsString(author);
        mockMvc.perform(post("/api/v1/authors")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(authorJsonString)

        )
                .andExpect(status().isOk())
                .andDo(print());
    }
}