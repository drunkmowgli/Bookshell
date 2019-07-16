package org.asm.labs.service.impl;

import lombok.SneakyThrows;
import org.asm.labs.model.Genre;
import org.asm.labs.repository.GenreRepository;
import org.asm.labs.service.GenreNotExistException;
import org.asm.labs.service.GenreService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@DisplayName("Genre Service test")
@SpringBootTest(properties = {
    InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
    ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
class GenreServiceImplTest {

    @Autowired
    GenreService genreService;
    
    @MockBean
    GenreRepository genreRepository;
    
    @Captor
    ArgumentCaptor<Genre> captor;


    @DisplayName("Должен корректно сохранять информацию о жанре")
    @Test
    void shouldSaveGenreInfo() {
        genreService.save("Genre Service #Test");
        verify(genreRepository).save(captor.capture());
        assertEquals("Genre Service #Test", captor.getValue().getName());
    }

    @DisplayName("Должен загружать список всех жанров с полной информацией о них")
    @Test
    void shouldReturnCorrectGenresListWithAllInfo() {
        assertTrue(genreService.findAll().isEmpty());
    }

    @DisplayName("Должен загружать информацию о нужном жанре")
    @Test
    @SneakyThrows
    void shouldFindExpectedGenreById() {
        when(genreRepository.findById("1234567890")).thenReturn(Optional.of(new Genre("Genre Service #Test")));
        String actualGenreName = genreService.findById("1234567890").getName();
        assertEquals("Genre Service #Test", actualGenreName);
    }

    @DisplayName("Должен выбрасывать исключение GenreNotExistException, если жанра не существует")
    @Test
    void shouldThrowGenreNotExistExceptionWhenGenreNotExist() {
        assertThrows(GenreNotExistException.class,
                () -> genreService.findById("123"));
    }

    @DisplayName("Должен удалять жанр")
    @Test
    @SneakyThrows
    void shouldRemoveGenre() {
        when(genreRepository.findById("1234567890")).thenReturn(Optional.of(new Genre("Genre Service #Test")));
        genreService.remove("1234567890");
        verify(genreRepository).delete(captor.capture());
        assertEquals("Genre Service #Test", captor.getValue().getName());
    }

    @DisplayName("Должен вернуть количество жанров")
    @Test
    void count() {
        assertThat(genreService.count()).isNotNull();
    }
}