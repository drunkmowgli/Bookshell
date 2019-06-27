package org.asm.labs.repository.impl;

import org.asm.labs.entity.Genre;
import org.asm.labs.repository.GenreRepositoryJpa;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DisplayName("Genre DAO/Repository test")
@DataJpaTest(properties = "spring.profiles.active=test")
@Import({GenreRepositoryJpaImpl.class})
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class GenreRepositoryJpaImplTest {

    @Autowired
    private GenreRepositoryJpa genreRepositoryJpa;

    @Autowired
    private TestEntityManager em;
    
    @DisplayName("Должен загружать список всех жанров с полной информацией о них")
    @Test
    void shouldReturnCorrectGenresListWithAllInfo() {
        List<Genre> genres = genreRepositoryJpa.findAll();
        assertThat(genres).isNotNull().hasSize(2).allMatch(g -> !g.getGenreName().equals(""));
    }
    
    @DisplayName("Должен загружать информацию о нужном жанре")
    @Test
    void shouldFindExpectedGenreById() {
        Genre actualGenre = genreRepositoryJpa.findById(1);
        Genre expectedGenre = em.find(Genre.class, 1);
        assertThat(actualGenre).isEqualToComparingFieldByField(expectedGenre);
    }
    
    @DisplayName("Должен выбрасывать исключение NoResultException, если жанра не существует")
    @Test
    void shouldThrowNoResultExceptionWhenGenreNotExist() {
        assertThrows(NoResultException.class,
                () -> genreRepositoryJpa.findById(10));
    }
    
    @DisplayName("Должен вернуть количество жанров")
    @Test
    void count() {
        assertEquals(2, genreRepositoryJpa.count());
    }

}