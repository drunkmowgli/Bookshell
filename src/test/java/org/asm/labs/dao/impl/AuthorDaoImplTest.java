package org.asm.labs.dao.impl;

import org.asm.labs.dao.AuthorDao;
import org.asm.labs.entity.Author;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("Author DAO/Repository test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(properties = "spring.profiles.active=test")
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class AuthorDaoImplTest {

    @Autowired
    AuthorDao authorDao;

    private Author author = new Author(2,"Author #Test");

    @DisplayName("This test should be add new record to testDB")
    @Test
    void add() {
        authorDao.add(author);
        assertEquals(2, author.getId());
    }

    @DisplayName("This test should return a record about Author Stan Lee by author name")
    @Test
    void getByName() {
        Author author = authorDao.getByName("Stan Lee");
        assertEquals("Stan Lee", author.getName());
    }

    @DisplayName("This test should return a record about Author Stan Lee by author id")
    @Test
    void getById() {
        Author author = authorDao.getById(1);
        assertEquals(1, author.getId());
        assertEquals("Stan Lee", author.getName());
    }

    @DisplayName("This test should delete a record about Author #Test")
    @Test
    void remove() {
        authorDao.remove(author);
    }

    @DisplayName("This test should return a count of authors")
    @Test
    void count() {
        assertEquals(3, authorDao.count());
    }

    @DisplayName("This test should return all authors")
    @Test
    void getAll() {
        assertEquals(2, authorDao.getAll().size());
        assertEquals("Stan Lee", authorDao.getAll().get(0).getName());
    }
}