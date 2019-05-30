package org.asm.labs.dao.impl;

import org.asm.labs.dao.AuthorDao;
import org.asm.labs.dao.BookDao;
import org.asm.labs.dao.GenreDao;
import org.asm.labs.entity.Author;
import org.asm.labs.entity.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Book DAO/Repository test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(properties = "spring.profiles.active=test")
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class BookDaoImplTest {

    @Autowired
    AuthorDao authorDao;

    @Autowired
    GenreDao genreDao;

    @Autowired
    BookDao bookDao;


    @DisplayName("Add new book to testDB")
    @Test
    void should_6_books_when_added() {
        List<Author> authors = Collections.singletonList(authorDao.getById(1));
        Book book = new Book("Test book from BookDao", authors, genreDao.getById(1));
        System.out.println(authors.toString());
        bookDao.add(book);
        assertEquals(6, bookDao.getAll().size());
    }

    @DisplayName("Add new book to testDB for throwing Exception check")
    @Test
    void should_throw_DataAccessException_if_book_exist() {
        Book existBook = bookDao.getById(1).orElseThrow();
        assertThrows(DataAccessException.class,
                () -> bookDao.add(existBook));
    }

    @DisplayName("Get all books from testDB")
    @Test
    void should_return_all_books() {
        assertEquals(5, bookDao.getAll().size());
    }

    @DisplayName("Get book by id from testDB")
    @Test
    void should_return_book() {
        Book book = bookDao.getById(1).orElseThrow();
        assertEquals(1, book.getId());
        assertEquals("Spider-Man #1", book.getTitle());
        assertEquals("Comics", book.getGenre().getGenreName());
    }

    @DisplayName("Get book by id from testDB")
    @Test
    void should_return_empty_when_book_not_exist() {
        assertFalse(bookDao.getById(10).isPresent());
    }

    @DisplayName("Remove book from testDB")
    @Test
    void remove() {
        Book book = bookDao.getById(1).orElseThrow();
        bookDao.remove(book);
        assertEquals(4, bookDao.getAll().size());
    }

    @DisplayName("Count books in testDB")
    @Test
    void count() {
        assertEquals(5, bookDao.count());
    }

}