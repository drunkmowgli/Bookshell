package org.asm.labs.repository.impl;

import org.asm.labs.repository.GenreDao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DisplayName("Genre DAO/Repository test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(properties = "spring.profiles.active=test")
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class GenreDaoImplTest {

    @Autowired
    GenreDao genreDao;


    @DisplayName("Get all genres from testDB")
    @Test
    void shouldReturnAllGenres() {
        assertEquals(2, genreDao.getAll().size());
    }

    @DisplayName("Get genre by id from testDB")
    @Test
    void shouldReturnComicsGenre() {
        assertEquals("Comics", genreDao.getById(1).getGenreName());
    }

    @DisplayName("Get genre by id from testDB")
    @Test
    void shouldThrowDataAccessExceptionWhenGenreNotExist() {
        assertThrows(DataAccessException.class,
                () -> genreDao.getById(10));
    }

    @DisplayName("Count genres in testDB")
    @Test
    void shouldReturn2Genres() {
        assertEquals(2, genreDao.count());
    }

}