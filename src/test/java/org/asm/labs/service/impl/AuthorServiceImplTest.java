package org.asm.labs.service.impl;

import org.asm.labs.service.AuthorAlreadyExistException;
import org.asm.labs.service.AuthorDoesntExistException;
import org.asm.labs.service.AuthorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Author Service test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(properties = "spring.profiles.active=test")
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class AuthorServiceImplTest {

    @Autowired
    AuthorService authorService;


    @DisplayName("Add Author to testDB")
    @Test
    void shouldAddAuthor() throws AuthorAlreadyExistException {
        authorService.add("Author Service #Test");
        assertEquals(4, authorService.getAll().size());
    }

    @DisplayName("Add author to testDB")
    @Test
    void shouldThrowAuthorAlreadyExistException() {
        assertThrows(AuthorAlreadyExistException.class,
                () -> authorService.add("Stan Lee"));
    }

    @DisplayName("Get all authors")
    @Test
    void shouldReturnAllAuthors() {
        assertFalse(authorService.getAll().isEmpty());
    }

    @DisplayName("Get author by id")
    @Test
    void shouldReturnAuthor() throws AuthorDoesntExistException {
        assertEquals(1, authorService.getById(1).getId());
        assertEquals("Stan Lee", authorService.getById(1).getName());
    }

    @DisplayName("Get author by id")
    @Test
    void shouldThrowAuthorDoesntExistExceptionWhenAuthorNotExist() {
        assertThrows(AuthorDoesntExistException.class,
                () -> authorService.getById(10));
    }

    @DisplayName("Remove author from TestDB")
    @Test
    void shouldRemoveAuthor() throws AuthorDoesntExistException {
        authorService.remove(1);
        assertEquals(2, authorService.getAll().size());
    }

    @DisplayName("Remove author from TestDB")
    @Test
    void shouldThrowAuthorDoesntExistExceptionWhenRemoveNotExistAuthor() {
        assertThrows(AuthorDoesntExistException.class,
                () -> {authorService.remove(10);});
    }

    @DisplayName("Count authors in TestDB")
    @Test
    void count() {
        assertEquals(3, authorService.count());
    }
}