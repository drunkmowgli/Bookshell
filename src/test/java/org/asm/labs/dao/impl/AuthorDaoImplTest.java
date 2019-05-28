package org.asm.labs.dao.impl;

import org.asm.labs.dao.AuthorDao;
import org.asm.labs.entity.Author;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DisplayName("Author DAO/Repository test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(properties = "spring.profiles.active=test")
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class AuthorDaoImplTest {

    @Autowired
    AuthorDao authorDao;


    private Author author = new Author("Author DAO #Test");

    @DisplayName("Add new author to testDB")
    @Test
    void add() {
        authorDao.add(author);
        assertEquals(3, authorDao.getAll().size());
    }

    @DisplayName("Check Exception on add to testDB")
    @Test
    void addException() {
        authorDao.add(author);
        assertThrows(DataAccessException.class,
                () -> {authorDao.add(author);});
    }

    @DisplayName("Get all authors from testDB")
    @Test
    void getAll() {
        assertEquals(2, authorDao.getAll().size());
        assertEquals("Stan Lee", authorDao.getAll().get(0).getName());
    }

    @DisplayName("Get author by name from testDB")
    @Test
    void getByName() {
        Author author = authorDao.getByName("Stan Lee");
        assertEquals("Stan Lee", author.getName());
    }

    @DisplayName("Get author by id from testDB")
    @Test
    void getById() {
        Author author = authorDao.getById(1);
        assertEquals(1, author.getId());
        assertEquals("Stan Lee", author.getName());
    }

    @DisplayName("Remove author from testDB")
    @Test
    void remove() {
        Author author = authorDao.getById(1);
        authorDao.remove(author);
        assertEquals(1, authorDao.getAll().size());
    }

    @DisplayName("Count authors in testDB")
    @Test
    void count() {
        assertEquals(2, authorDao.count());
    }

    @Test
    void getByBookId() {
        System.out.println(authorDao.getByBookId(1));
    }
}