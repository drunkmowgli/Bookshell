package org.asm.labs.service.impl;

import org.asm.labs.service.GenreDoesntExistException;
import org.asm.labs.service.GenreService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DisplayName("Genre Service test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(properties = "spring.profiles.active=test")
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class GenreServiceImplTest {

    @Autowired
    GenreService genreService;


    @DisplayName("Get all genres from testDB")
    @Test
    void should_return_all_genres() {
        assertEquals(2, genreService.getAll().size());
    }

    @DisplayName("Get genre by id from testDB")
    @Test
    void should_return_genre() throws GenreDoesntExistException {
        assertEquals(1, genreService.getById(1).getId());
    }

    @DisplayName("Get genre by id from testDB")
    @Test
    void should_throw_GenreDoesntExistException() {
        assertThrows(GenreDoesntExistException.class,
                () -> genreService.getById(10));
    }

    @DisplayName("Count genres in testDB")
    @Test
    void count() {
        assertEquals(2, genreService.count());
    }
}