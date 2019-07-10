package org.asm.labs.service.impl;

import org.asm.labs.service.GenreNotExistException;
import org.asm.labs.service.GenreService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Genre Service test")
@DataJpaTest
@Import({GenreServiceImpl.class})
class GenreServiceImplTest {
    
    @Autowired
    GenreService genreService;
    
    
    @DisplayName("Должен загружать список всех жанров с полной информацией о них")
    @Test
    void shouldReturnCorrectGenresListWithAllInfo() {
        assertFalse(genreService.findAll().isEmpty());
    }
    
    @DisplayName("Должен загружать информацию о нужном жанре")
    @Test
    void shouldFindExpectedGenreById() throws GenreNotExistException {
        assertEquals(1, genreService.findById(1).getId());
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
        assertThat(genreService.count()).isNotNull();
    }
}