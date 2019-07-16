package org.asm.labs.service.impl;

import lombok.SneakyThrows;
import org.asm.labs.model.Author;
import org.asm.labs.model.Book;
import org.asm.labs.model.Comment;
import org.asm.labs.model.Genre;
import org.asm.labs.repository.BookRepository;
import org.asm.labs.repository.CommentRepository;
import org.asm.labs.service.CommentNotExistException;
import org.asm.labs.service.CommentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@DisplayName("Comment Service test")
@SpringBootTest(properties = {
    InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
    ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
class CommentServiceImplTest {
    
    @Autowired
    private CommentService commentService;
    
    @MockBean
    BookRepository bookRepository;
    
    @MockBean
    CommentRepository commentRepository;
    
    @Captor
    ArgumentCaptor<Comment> captor;
    
    
    @DisplayName("Должен корректно сохранять информацию о комментарии")
    @Test
    @SneakyThrows
    void shouldSaveCommentInfo() {
        when(bookRepository.findById("1234567890")).thenReturn(Optional.of(new Book(
            "Book Service #Test",
            Collections.singleton(new Author("Author Service #Test")),
            new Genre("Genre Service Test"))));
        commentService.save("Comment Service #Test", "1234567890");
        verify(commentRepository).save(captor.capture());
        assertEquals("Comment Service #Test", captor.getValue().getDescription());
    }
    
    @DisplayName("Должен вернуть информацию о всех комментариях")
    @Test
    void shouldReturnCorrectCommentsListWithAllInfo() {
        assertTrue(commentService.findAll().isEmpty());
    }
    
    @DisplayName("Должен загружать информацию о нужном комментарии")
    @Test
    @SneakyThrows
    void shouldFindExpectedCommentById() {
        when(bookRepository.findById("1234567890")).thenReturn(Optional.of(new Book(
            "Book Service #Test",
            Collections.singleton(new Author("Author Service #Test")),
            new Genre("Genre Service Test"))));
        when(commentRepository.findById("1234567890")).thenReturn(Optional.of(new Comment("Comment Service #Test")));
        String actualDescription = commentService.findById("1234567890").getDescription();
        assertEquals("Comment Service #Test", actualDescription);
    }
    
    @DisplayName("Должен выбрасывать исключение CommentNotExistException, если комментария не существует")
    @Test
    void shouldThrowCommentNotExistExceptionWhenCommentNotExist() {
        assertThrows(CommentNotExistException.class,
            () -> commentService.findById("123"));
    }
    
    @DisplayName("Должен удалять комментарий")
    @Test
    @SneakyThrows
    void shouldRemoveComment() {
        when(bookRepository.findById("1234567890")).thenReturn(Optional.of(new Book(
            "Book Service #Test",
            Collections.singleton(new Author("Author Service #Test")),
            new Genre("Genre Service Test"))));
        when(commentRepository.findById("1234567890")).thenReturn(Optional.of(new Comment("Comment Service #Test")));
        commentService.remove("1234567890");
        verify(commentRepository).delete(captor.capture());
        assertEquals("Comment Service #Test", captor.getValue().getDescription());
    }
    
    @DisplayName("Должен вернуть количество комментариев")
    @Test
    void count() {
        assertThat(commentService.count()).isNotNull();
    }
}