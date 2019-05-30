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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


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
    void should_add_author() throws AuthorAlreadyExistException {
        authorService.add("Author Service #Test");
        assertEquals(4, authorService.getAll().size());
        assertThrows(AuthorAlreadyExistException.class,
                () -> authorService.add("Author Service #Test"));
    }

    @DisplayName("Add author to testDB")
    @Test
    void should_throw_AuthorAlreadyExistException() {
        assertThrows(AuthorAlreadyExistException.class,
                () -> authorService.add("Stan Lee"));
    }

    @DisplayName("Get all authors")
    @Test
    void should_return_all_authors() {
        assertEquals(3, authorService.getAll().size());
    }

    @DisplayName("Get author by id")
    @Test
    void should_return_author() throws AuthorDoesntExistException {
        assertEquals(1, authorService.getById(1).getId());
        assertEquals("Stan Lee", authorService.getById(1).getName());
    }

    @DisplayName("Get author by id")
    @Test
    void should_throw_AuthorDoesntExistException_when_author_not_exist() {
        assertThrows(AuthorDoesntExistException.class,
                () -> authorService.getById(10));
    }

    @DisplayName("Remove author from TestDB")
    @Test
    void should_remove_author() throws AuthorDoesntExistException {
        authorService.remove(1);
        assertEquals(2, authorService.getAll().size());
    }

    @DisplayName("Remove author from TestDB")
    @Test
    void should_throw_AuthorDoesntExistException_when_remove_not_exist_author() {
        assertThrows(AuthorDoesntExistException.class,
                () -> {authorService.remove(10);});
    }

    @DisplayName("Count authors in TestDB")
    @Test
    void count() {
        assertEquals(3, authorService.count());
    }
}