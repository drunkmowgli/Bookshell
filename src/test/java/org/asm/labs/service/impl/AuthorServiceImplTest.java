package org.asm.labs.service.impl;

import org.asm.labs.entity.Author;
import org.asm.labs.service.AuthorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("Author Service test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(properties = "spring.profiles.active=test")
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class AuthorServiceImplTest {

    @Autowired
    AuthorService authorService;

    private Author author = new Author(3, "Author Service #Test");

    @DisplayName("Add Author")
    @Test
    void add() {
        authorService.add(author);
        assertEquals(4, authorService.getAll().size());
    }

    @DisplayName("Get all authors")
    @Test
    void getAll() {
        assertEquals(3, authorService.getAll().size());
        assertEquals("Stan Lee", authorService.getAll().get(0).getName());
    }

    @DisplayName("Get author by name")
    @Test
    void getByName() {
        assertEquals("Author Service #Test", authorService.getByName(author.getName()).getName());
    }

    @DisplayName("Get author by id")
    @Test
    void getById() {
        assertEquals(1, authorService.getById(1).getId());
        assertEquals("Stan Lee", authorService.getById(1).getName());
    }

    @DisplayName("Remove author from TestDB")
    @Test
    void remove() {
        authorService.remove(author);
        assertEquals(3, authorService.getAll().size());
    }

    @DisplayName("Count authors in TestDB")
    @Test
    void count() {
        assertEquals(4, authorService.count());
    }
}