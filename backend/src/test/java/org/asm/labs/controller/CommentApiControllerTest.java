package org.asm.labs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.asm.labs.model.Author;
import org.asm.labs.model.Book;
import org.asm.labs.model.Comment;
import org.asm.labs.model.Genre;
import org.asm.labs.service.CommentService;
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

@WebMvcTest(CommentApiController.class)
class CommentApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService commentService;


    @DisplayName("Должен вернуть JSON содержащий информацию о всех комментариях к книге")
    @Test
    @SneakyThrows
    void shouldReturnStatusCode200onGetAllCommentToBook() {
        Book book = new Book(
                0,
                "Book MVC #Test",
                Collections.singleton(new Author("Author MVC #Test")),
                new Genre(0, "Genre MVC #Test")
        );
        when(
                commentService.findByBookId(0)).thenReturn(Collections.singletonList(
                new Comment("Comment MVC #Test", book)
                )
        );
        mockMvc.perform(get("/api/v1/books/{id}/comments", "0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(0)))
                .andExpect(jsonPath("$[0].commentDescription", is("Comment MVC #Test")));
        verify(commentService, times(1)).findByBookId(0);
        verifyNoMoreInteractions(commentService);

    }

    @DisplayName("Должен добавить комментарий к книге, затем вернуть JSON и статус isCreated (201)")
    @Test
    @SneakyThrows
    void shouldReturnStatusCode200OnAddComment() {
        String commentDescription = "Comment MVC #Test";
        Book book = new Book(
                0,
                "Book MVC #Test",
                Collections.singleton(new Author("Author MVC #Test")),
                new Genre(0, "Genre MVC #Test")
        );
        Comment comment = new Comment(commentDescription, book);
        when(commentService.save(commentDescription, 0L)).thenReturn(comment);
        ObjectMapper objectMapper = new ObjectMapper();
        String commentJsonString = objectMapper.writeValueAsString(comment);
        mockMvc.perform(
                post(
                        "/api/v1/books/{id}/comments", "0"
                )
                        .content(commentJsonString)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(status().isOk())
                .andDo(print());

    }
}