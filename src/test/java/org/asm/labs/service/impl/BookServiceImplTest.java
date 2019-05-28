package org.asm.labs.service.impl;

import org.asm.labs.entity.Book;
import org.asm.labs.entity.Genre;
import org.asm.labs.service.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


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
    void add() {
        String bookTitle = "Test title from BookService";
        String authorsNames = "Stan Lee,Jack Kirby";
        String genreName = "Comics";
        bookService.add(bookTitle, authorsNames, genreName);
        assertEquals(3, bookService.count());
    }

    @DisplayName("Check Exception on add to testDB")
    @Test
    void addException() {
        String bookTitle = "Test title from BookService";
        String authorsNames = "Stan Lee,Jack Kirby";
        String genreName = "Comics";
        bookService.add(bookTitle, authorsNames, genreName);
        assertThrows(BookAlreadyExistException.class,
                () -> {bookService.add(bookTitle, authorsNames, genreName);});
    }


    @DisplayName("Get all books")
    @Test
    void getAll() {
        assertEquals(2, bookService.getAll().size());
        assertEquals("Hulk #2", bookService.getAll().get(1).getTitle());
    }

    @DisplayName("Get book by Title")
    @Test
    void getByTitle() {
        assertEquals("Spider-Man #1", bookService.getByTitle("Spider-Man #1").getTitle());
        assertThrows(BookDoesntExistException.class,
                () -> {bookService.getByTitle("Doesnt exist book");});
    }

    @DisplayName("Get books by Genre")
    @Test
    void getAllByGenre() {
        List<Book> books = bookService.getAllByGenre(new Genre("Comics"));
        assertEquals(2, books.size());
    }

    @DisplayName("Get book by id")
    @Test
    void getById() {
        assertEquals(2, bookService.getById(2).getId());
        assertEquals("Hulk #2", bookService.getById(2).getTitle());
    }

    @DisplayName("Remove book from TestDB")
    @Test
    void remove() {
        bookService.remove("Hulk #2");
        assertEquals(1, bookService.getAll().size());
        assertThrows(BookDoesntExistException.class,
                () -> {bookService.remove("Doesnt Exists Book");});
    }

    @DisplayName("Count books in TestDB")
    @Test
    void count() {
        assertEquals(2, bookService.count());
    }

}