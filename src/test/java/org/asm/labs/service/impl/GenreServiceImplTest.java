package org.asm.labs.service.impl;

import org.asm.labs.repository.impl.GenreRepositoryJpaImpl;
import org.asm.labs.service.GenreNotExistException;
import org.asm.labs.service.GenreService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Genre Service test")
@DataJpaTest(properties = "spring.profiles.active=test")
@Import({GenreServiceImpl.class, GenreRepositoryJpaImpl.class})
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
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

    @DisplayName("Должен выбрасывать исключение NoResultException, если жанра не существует")
    @Test
    void shouldThrowNoResultExceptionWhenGenreNotExist() {
        assertThrows(GenreNotExistException.class,
                () -> genreService.findById(10));
    }

    @DisplayName("Должен вернуть количество жанров")
    @Test
    void count() {
        assertEquals(2, genreService.count());
    }
}