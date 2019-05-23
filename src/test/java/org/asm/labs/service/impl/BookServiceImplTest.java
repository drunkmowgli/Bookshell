package org.asm.labs.service.impl;

import org.asm.labs.entity.Author;
import org.asm.labs.entity.Book;
import org.asm.labs.entity.Genre;
import org.asm.labs.service.BookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("Book Service test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(properties = "spring.profiles.active=test")
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class BookServiceImplTest {

    @Autowired
    BookService bookService;
    
    
    private Book book = new Book(3, "Test Horror Book #1",
            new Author(1,"Test Author from BookService"),
            new Genre(2, "Horror"));
    
    @DisplayName("Add book")
    @Test
    void add() {
        bookService.add(book);
        assertEquals(3, bookService.getAll().size());
    }

    @DisplayName("Get all books")
    @Test
    void getAll() {
        assertEquals(3, bookService.getAll().size());
        assertEquals("Hulk #2", bookService.getAll().get(1).getTitle());
    }

    @DisplayName("Get book by Title")
    @Test
    void getByTitle() {
        assertEquals("Test Horror Book #1", bookService.getByTitle("Test Horror Book #1").getTitle());
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
        bookService.remove(book);
        assertEquals(2, bookService.getAll().size());
    }

    @DisplayName("Count books in TestDB")
    @Test
    void count() {
        assertEquals(3, bookService.count());
    }

}