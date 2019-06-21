package org.asm.labs.service.impl;

import org.asm.labs.service.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Book Service test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(properties = "spring.profiles.active=test")
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class BookServiceImplTest {

    @Autowired
    BookService bookService;

    @Autowired
    AuthorService authorService;

    @Autowired
    GenreService genreService;


    @DisplayName("Add book")
    @Test
    void shouldAddBook() throws GenreDoesntExistException, AuthorDoesntExistException, BookAlreadyExistException {
        String bookTitle = "Test title from BookService";
        String authorsIds = "1,2";
        int genreId = 1;
        bookService.add(bookTitle, authorsIds, genreId);
        assertEquals(6, bookService.getAll().size());
    }

    @DisplayName("Add book GenreDoesntExistException")
    @Test
    void shouldThrowGenreDoesntExistExceptionWhenGenreDoesntExist() {
        String bookTitle = "Test title from BookService";
        String authorsIds = "1,2";
        int genreId = 10;
        assertThrows(GenreDoesntExistException.class,
                () -> bookService.add(bookTitle, authorsIds, genreId));
    }

    @DisplayName("Add book AuthorDoesntExist")
    @Test
    void shouldThrowAuthorDoesntExistExceptionWhenAuthorsDoesntExist() {
        String bookTitle = "Test title from BookService";
        String authorsIds = "10,2";
        int genreId = 1;
        assertThrows(AuthorDoesntExistException.class,
                () -> bookService.add(bookTitle, authorsIds, genreId));
    }

    @DisplayName("Add book AuthorDoesntExist")
    @Test
    void shouldThrowBookAlreadyExistExceptionWhenBookAlreadyExist() {
        String bookTitle = "Hulk #2";
        String authorsIds = "1,2";
        int genreId = 1;
        assertThrows(BookAlreadyExistException.class,
                () -> bookService.add(bookTitle, authorsIds, genreId));
    }


    @DisplayName("Get all books")
    @Test
    void shouldReturnAllBooks() {
        assertFalse(bookService.getAll().isEmpty());
    }

    @DisplayName("Get book by id")
    @Test
    void shouldReturnBook() throws BookDoesntExistException {
        assertEquals(2, bookService.getById(2).getId());
        assertEquals("Dark Horse Comics #1", bookService.getById(2).getTitle());
    }

    @DisplayName("Get book by id BookDoesntExist")
    @Test
    void shouldThrowBookDoesntExistExceptionWhenBookNotExist() {
        assertThrows(BookDoesntExistException.class,
                () -> bookService.getById(10));
    }


    @DisplayName("Remove book from TestDB")
    @Test
    void shouldRemoveBook() throws BookDoesntExistException {
        bookService.remove(1);
        assertEquals(4, bookService.getAll().size());
    }

    @DisplayName("Count books in TestDB")
    @Test
    void count() {
        assertEquals(5, bookService.count());
    }


}