package org.asm.labs.service.impl;

import lombok.SneakyThrows;
import org.asm.labs.model.Genre;
import org.asm.labs.repository.GenreRepository;
import org.asm.labs.service.GenreNotExistException;
import org.asm.labs.service.GenreService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@DisplayName("Genre Service test")
@SpringBootTest
class GenreServiceImplTest {

    @Autowired
    private GenreService genreService;
    
    @MockBean
    private GenreRepository genreRepository;


    @DisplayName("Должен загружать список всех жанров с полной информацией о них")
    @Test
    void shouldReturnCorrectGenresListWithAllInfo() {
        when(genreRepository.findAll()).thenReturn(Collections.singletonList(
            new Genre(0L, "Genre Service #Test"))
        );
        List<Genre> genreServiceAll = genreService.findAll();
        assertThat(genreServiceAll).isNotNull();
        Genre genre = genreServiceAll.get(0);
        assertEquals("Genre Service #Test", genre.getGenreName());
    }

    @DisplayName("Должен загружать информацию о нужном жанре")
    @Test
    @SneakyThrows
    void shouldFindExpectedGenreById() {
        when(genreRepository.findById(0L)).thenReturn(Optional.of(new Genre(0L, "Genre Service #Test")));
        Genre actualGenre = genreService.findById(0L);
        assertEquals("Genre Service #Test", actualGenre.getGenreName());
    }

    @DisplayName("Должен выбрасывать исключение GenreNotExistException, если жанра не существует")
    @Test
    void shouldThrowGenreNotExistExceptionWhenGenreNotExist() {
        assertThrows(GenreNotExistException.class,
                () -> genreService.findById(10));
    }

    @DisplayName("Должен вернуть количество жанров")
    @Test
    void count() {
        when(genreRepository.count()).thenReturn(2L);
        assertThat(genreService.count()).isNotNull();
        assertEquals(2L, genreService.count());
    }
    
    @DisplayName("Должен вернуть жанр по названию")
    @Test
    @SneakyThrows
    void shouldReturnGenreByGenreName() {
        when(genreRepository.findByGenreName("Genre Service #Test")).thenReturn(Optional.of(new Genre(
            0L, "Genre Service #Test")));
        Genre genre = genreService.findByGenreName("Genre Service #Test");
        assertEquals("Genre Service #Test", genre.getGenreName());
    }
}