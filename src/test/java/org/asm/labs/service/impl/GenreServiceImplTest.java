package org.asm.labs.service.impl;

import org.asm.labs.service.GenreNotExistException;
import org.asm.labs.service.GenreService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Genre Service test")
@DataMongoTest
@Import({GenreServiceImpl.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ComponentScan("org.asm.labs.events")
class GenreServiceImplTest {

    @Autowired
    GenreService genreService;


    @DisplayName("Должен корректно сохранять информацию о жанре")
    @Test
    void shouldSaveGenreInfo() {
        long beforeInsert = genreService.findAll().size();
        genreService.save("Genre Service #Test");
        long afterInsert = genreService.findAll().size();
        System.out.println(genreService.findAll());
        assertThat(afterInsert).isGreaterThan(beforeInsert);
    }

    @DisplayName("Должен загружать список всех жанров с полной информацией о них")
    @Test
    void shouldReturnCorrectGenresListWithAllInfo() {
        assertTrue(genreService.findAll().isEmpty());
    }

    @DisplayName("Должен загружать информацию о нужном жанре")
    @Test
    void shouldFindExpectedGenreById() throws GenreNotExistException {
        genreService.save("Genre Service #Test");
        String genreId = genreService.findAll().get(0).getId();
        assertThat(genreId).isNotNull();
        assertEquals("Genre Service #Test", genreService.findById(genreId).getName());
    }

    @DisplayName("Должен выбрасывать исключение GenreNotExistException, если жанра не существует")
    @Test
    void shouldThrowGenreNotExistExceptionWhenGenreNotExist() {
        assertThrows(GenreNotExistException.class,
                () -> genreService.findById("123"));
    }

    @DisplayName("Должен удалять жанр")
    @Test
    void shouldRemoveGenre() throws GenreNotExistException {
        genreService.save("Genre Service #Test");
        long beforeDelete = genreService.findAll().size();
        String genreId = genreService.findAll().get(0).getId();
        genreService.remove(genreId);
        long afterDelete = genreService.findAll().size();
        assertThat(afterDelete).isLessThan(beforeDelete);
    }

    @DisplayName("Должен вернуть количество жанров")
    @Test
    void count() {
        assertThat(genreService.count()).isNotNull();
    }
}