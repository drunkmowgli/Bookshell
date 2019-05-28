package org.asm.labs.dao.impl;

import org.asm.labs.dao.AuthorDao;
import org.asm.labs.dao.BookDao;
import org.asm.labs.dao.GenreDao;
import org.asm.labs.entity.Author;
import org.asm.labs.entity.Book;
import org.asm.labs.entity.Genre;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


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
    void add() {
        authorDao.add(new Author("Test author from BookDao"));
        genreDao.add(new Genre("Test Genre from BookDao"));
        List<Author> authors = Collections.singletonList(authorDao.getByName("Test author from BookDao"));
        Book book = new Book("Test book from BookDao", authors, genreDao.getByGenreName("Test Genre from BookDao"));
        bookDao.add(book);
        assertEquals(6, bookDao.getAll().size());
    }

    @DisplayName("Check Exception on add to testDB")
    @Test
    void addException() {
        authorDao.add(new Author("Test author from BookDao"));
        genreDao.add(new Genre("Test Genre from BookDao"));
        List<Author> authors = Collections.singletonList(authorDao.getByName("Test author from BookDao"));
        Book book = new Book("Test book from BookDao", authors, genreDao.getByGenreName("Test Genre from BookDao"));
        bookDao.add(book);
        assertThrows(DataAccessException.class,
                () -> {bookDao.add(book);});
    }

    @DisplayName("Get all books from testDB")
    @Test
    void getAll() {
        System.out.println(bookDao.getAll());
        assertEquals(5, bookDao.getAll().size());
    }

    @DisplayName("Get book by title from testDB")
    @Test
    void getByTitle() {
        assertEquals("Spider-Man #1", bookDao.getByTitle("Spider-Man #1").getTitle());
    }

    @DisplayName("Get book by id from testDB")
    @Test
    void getById() {
        assertEquals(1, bookDao.getById(1).getId());
    }

    @DisplayName("Get all books by genre from testDB")
    @Test
    void getAllByGenre() {
        assertEquals(5, bookDao.getAllByGenre(new Genre("Comics")).size());
        assertEquals("Comics", bookDao.getAllByGenre(new Genre("Comics")).get(0)
                .getGenre()
                .getGenreName());
    }

    @DisplayName("Get all books by author from testDB")
    @Test
    void getAllByAuthor() {
        Author author = authorDao.getByName("Stan Lee");
        assertEquals(2, bookDao.getAllByAuthor(author).size());
    }

    @DisplayName("Remove book from testDB")
    @Test
    void remove() {
        Book book = bookDao.getById(1);
        bookDao.remove(book);
        assertEquals(4, bookDao.getAll().size());
    }

    @DisplayName("Count books in testDB")
    @Test
    void count() {
        assertEquals(5, bookDao.count());
    }

}