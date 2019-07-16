package org.asm.labs.service.impl;

import lombok.SneakyThrows;
import org.asm.labs.model.Author;
import org.asm.labs.repository.AuthorRepository;
import org.asm.labs.service.AuthorNotExistException;
import org.asm.labs.service.AuthorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Author Service test")
@SpringBootTest(properties = {
    InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
    ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
class AuthorServiceImplTest {
    
    @Autowired
    private AuthorService authorService;
    
    @MockBean
    private AuthorRepository authorRepository;
    
    @Captor
    ArgumentCaptor<Author> captor;
    
    @DisplayName("Должен корректно сохранять информацию об авторе")
    @Test
    void shouldSaveAuthorInfo() {
        authorService.save("Author Service #Test");
        verify(authorRepository).save(captor.capture());
        assertThat(captor.getAllValues()).isNotNull();
        assertEquals("Author Service #Test", captor.getValue().getName());
        
    }
    
    @DisplayName("Должен вернуть информацию о всех авторах")
    @Test
    void shouldReturnCorrectAuthorsListWithAllInfo() {
        List<Author> authors = authorService.findAll();
        assertTrue(authors.isEmpty());
    }
    
    @DisplayName("Должен загружать информацию о нужном авторе")
    @Test
    @SneakyThrows
    void shouldFindExpectedAuthorById() {
        when(authorRepository.findById("1234567890")).thenReturn(Optional.of(new Author("Author Service #Test")));
        String actualAuthorName = authorService.findById("1234567890").getName();
        assertEquals("Author Service #Test", actualAuthorName);
    }
    
    @DisplayName("Должен выбрасывать исключение AuthorNotExistException, если автора не существует")
    @Test
    void shouldThrowAuthorNotExistExceptionWhenAuthorNotExist() {
        assertThrows(AuthorNotExistException.class,
            () -> authorService.findById("123"));
    }
    
    @DisplayName("Должен удалять автора")
    @Test
    @SneakyThrows
    void shouldRemoveAuthor() {
        when(authorRepository.findById("1234567890")).thenReturn(Optional.of(new Author("Author Service #Test")));
        authorService.delete("1234567890");
        verify(authorRepository).delete(captor.capture());
        assertEquals("Author Service #Test", captor.getValue().getName());
    }
    
    @DisplayName("Должен вернуть количество авторов")
    @Test
    void count() {
        assertThat(authorService.count()).isNotNull();
    }
}