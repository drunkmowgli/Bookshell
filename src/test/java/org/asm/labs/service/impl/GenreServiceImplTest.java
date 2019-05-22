package org.asm.labs.service.impl;

import org.asm.labs.entity.Genre;
import org.asm.labs.service.GenreService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("Book Service test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(properties = "spring.profiles.active=test")
@Transactional(propagation = Propagation.NOT_SUPPORTED)
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
    
    @DisplayName("Get all genres from testDB")
    @Test
    void getAll() {
        assertEquals(2, genreService.getAll().size());
    }
}