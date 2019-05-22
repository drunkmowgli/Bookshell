package org.asm.labs.dao.impl;

import org.asm.labs.dao.BookDao;
import org.asm.labs.entity.Author;
import org.asm.labs.entity.Book;
import org.asm.labs.entity.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("Book DAO/Repository test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(properties = "spring.profiles.active=test")
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class BookDaoImplTest {
    
    @Autowired
    BookDao bookDao;

    
    private Book book = new Book("Test Comic Book #1",
                        new Author(1,"Test Stan Lee"),
                        new Genre(1, "Comics"));


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

    @DisplayName("Remove book from testDB")
    @Test
    void remove() {
        bookDao.remove(book);
        assertEquals(2, bookDao.getAll().size());
    }

    @DisplayName("Count books in testDB")
    @Test
    void count() {
        assertEquals(3, bookDao.count());
    }

}