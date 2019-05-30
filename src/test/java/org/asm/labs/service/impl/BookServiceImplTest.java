package org.asm.labs.service.impl;

import org.asm.labs.service.AuthorService;
import org.asm.labs.service.BookService;
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


//    @DisplayName("Add book")
//    @Test
//    void add() {
//        String bookTitle = "Test title from BookService";
//        String authorsNames = "Stan Lee,Jack Kirby";
//        String genreName = "Comics";
//        bookService.add(bookTitle, authorsNames, genreName);
//        assertEquals(6, bookService.count());
//    }

//    @DisplayName("Check Exception on add to testDB")
//    @Test
//    void addException() {
//        String bookTitle = "Test title from BookService";
//        String authorsNames = "Stan Lee,Jack Kirby";
//        String genreName = "Comics";
//        bookService.add(bookTitle, authorsNames, genreName);
//        assertThrows(BookAlreadyExistException.class,
//                () -> {bookService.add(bookTitle, authorsNames, genreName);});
//    }


    @DisplayName("Get all books")
    @Test
    void getAll() {
        assertEquals(5, bookService.getAll().size());
        assertEquals("Dark Horse Comics #1", bookService.getAll().get(1).getTitle());
    }

//    @DisplayName("Get book by id")
//    @Test
//    void getById() {
//        assertEquals(2, bookService.getById(2).getId());
//        assertEquals("Dark Horse Comics #1", bookService.getById(2).getTitle());
//    }

//    @DisplayName("Remove book from TestDB")
//    @Test
//    void remove() {
//        bookService.remove("Hulk #2");
//        assertEquals(4, bookService.getAll().size());
//    }

    @DisplayName("Count books in TestDB")
    @Test
    void count() {
        assertEquals(5, bookService.count());
    }


}