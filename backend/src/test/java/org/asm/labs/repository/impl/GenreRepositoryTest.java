package org.asm.labs.repository.impl;

import org.asm.labs.model.Genre;
import org.asm.labs.repository.GenreRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;


@DisplayName("Genre Repository test")
@DataJpaTest
@Transactional
@AutoConfigureTestDatabase(replace = NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class GenreRepositoryTest {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private TestEntityManager em;

    @DisplayName("Должен загружать список всех жанров с полной информацией о них")
    @Test
    void shouldReturnCorrectGenresListWithAllInfo() {
        List<Genre> genres = genreRepository.findAll();
        assertThat(genres).isNotNull().allMatch(g -> !g.getGenreName().equals(""));
    }

    @DisplayName("Должен загружать информацию о нужном жанре")
    @Test
    void shouldFindExpectedGenreById() {
        Genre actualGenre = genreRepository.findById(1L).orElseThrow();
        Genre expectedGenre = em.find(Genre.class, 1L);
        assertThat(actualGenre).isEqualToComparingFieldByField(expectedGenre);
    }

    @DisplayName("Должен вернуть количество жанров")
    @Test
    void count() {
        assertThat(genreRepository.count()).isNotNull();
    }

}