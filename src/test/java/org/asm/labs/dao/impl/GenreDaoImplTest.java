package org.asm.labs.dao.impl;

import org.asm.labs.dao.GenreDao;
import org.asm.labs.entity.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("Genre DAO/Repository test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(properties = "spring.profiles.active=test")
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class GenreDaoImplTest {

    @Autowired
    GenreDao genreDao;

    private Genre genre = new Genre("Roman");
    private Genre genreConflict = new Genre("Roman");

    @DisplayName("Add new genre to testDB")
    @Test
    void add() {
        genreDao.add(genre);
        genreDao.add(genreConflict);
        assertEquals(3, genreDao.getAll().size());
    }

    @DisplayName("Get all genres from testDB")
    @Test
    void getAll() {
        assertEquals(2, genreDao.getAll().size());
    }

    @DisplayName("Get genre by Genre's name from testDB")
    @Test
    void getByGenreName() {
        assertEquals("Comics", genreDao.getByGenreName("Comics").getGenreName());
    }

    @DisplayName("Get genre by id from testDB")
    @Test
    void getById() {
        assertEquals("Comics", genreDao.getById(1).getGenreName());
    }

    @DisplayName("Count genres in testDB")
    @Test
    void count() {
        assertEquals(2, genreDao.count());
    }
}