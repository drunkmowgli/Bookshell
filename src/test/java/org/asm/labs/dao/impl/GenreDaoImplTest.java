package org.asm.labs.dao.impl;

import org.asm.labs.dao.GenreDao;
import org.asm.labs.entity.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("Book DAO/Repository test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(properties = "spring.profiles.active=test")
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class GenreDaoImplTest {

    @Autowired
    GenreDao genreDao;

    private Genre genre = new Genre("Roman");
    private Genre genreConflict = new Genre("Roman");

    @DisplayName("Add Genre to testDB")
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
}