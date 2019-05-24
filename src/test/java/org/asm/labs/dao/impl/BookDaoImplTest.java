package org.asm.labs.dao.impl;

import org.asm.labs.dao.AuthorDao;
import org.asm.labs.dao.BookDao;
import org.asm.labs.entity.Author;
import org.asm.labs.entity.Book;
import org.asm.labs.entity.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("Book DAO/Repository test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(properties = "spring.profiles.active=test")
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class BookDaoImplTest {

    @Autowired
    AuthorDao authorDao;

    @Autowired
    BookDao bookDao;

    List<Author> authors = new ArrayList<>(Collections.singletonList(new Author("Test Author from BookDao")));
    
    private Book book = new Book("Test Horror Book #1",
                        authors,
                        new Genre(2, "Horror"));


    @DisplayName("Add new book to testDB")
    @Test
    void add() {
        bookDao.add(book);
        assertEquals(3, bookDao.getAll().size());
    }

    @DisplayName("Get all books from testDB")
    @Test
    void getAll() {
        System.out.println(bookDao.getAll());
        assertEquals(2, bookDao.getAll().size());
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
        assertEquals(2, bookDao.getAllByGenre(new Genre("Comics")).size());
        assertEquals("Comics", bookDao.getAllByGenre(new Genre("Comics")).get(0)
                .getGenre()
                .getGenreName());
    }

    @DisplayName("Get all books by author from testDB")
    @Test
    void getAllByAuthor() {
        Author author = authorDao.getByName("Stan Lee");
        assertEquals(1, bookDao.getAllByAuthor(author).size());
    }

    @DisplayName("Remove book from testDB")
    @Test
    void remove() {
        Book book = bookDao.getById(1);
        bookDao.remove(book);
        assertEquals(1, bookDao.getAll().size());
    }

    @DisplayName("Count books in testDB")
    @Test
    void count() {
        assertEquals(2, bookDao.count());
    }

}