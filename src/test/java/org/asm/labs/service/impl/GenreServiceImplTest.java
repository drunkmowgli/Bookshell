package org.asm.labs.service.impl;

import org.asm.labs.entity.Genre;
import org.asm.labs.service.GenreAlreadyExistException;
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

    private Genre genre = new Genre(3, "Fantasy");

    @DisplayName("Add Genre to testDB")
    @Test
    void add() {
        genreService.add(genre);
        assertEquals(3, genreService.getAll().size());
    }

    @DisplayName("Check Exception on add to testDB")
    @Test
    void addException() {
        genreService.add(genre);
        assertThrows(GenreAlreadyExistException.class,
                () -> {genreService.add(genre);});
    }

    @DisplayName("Get all genres from testDB")
    @Test
    void getAll() {
        assertEquals(2, genreService.getAll().size());
    }

    @DisplayName("Get genre by genre's name from testDB")
    @Test
    void getByGenreName() {
        assertEquals("Comics", genreService.getByGenreName("Comics").getGenreName());
        assertThrows(GenreDoesntExistException.class,
                () -> {genreService.getByGenreName("Genre doesnt exist");});
    }

    @DisplayName("Get genre by id from testDB")
    @Test
    void getById() {
        assertEquals(1, genreService.getById(1).getId());
    }

    @DisplayName("Count genres in testDB")
    @Test
    void count() {
        assertEquals(2, genreService.count());
    }

    @DisplayName("Remove genre from testDB")
    @Test
    void remove() {
        genreService.remove("Comics");
        assertEquals(1, genreService.getAll().size());
        assertThrows(GenreDoesntExistException.class,
                () -> {genreService.remove("Doesnt Exists Genre");});
    }
}