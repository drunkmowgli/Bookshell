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


    @DisplayName("Add new author to testDB")
    @Test
    void should_4_authors_when_added() {
        Author author = new Author("Author DAO #Test");
        authorDao.add(author);
        assertEquals(4, authorDao.getAll().size());
    }

    @DisplayName("Add new author to testDB for throwing Exception check")
    @Test
    void should_throw_DataAccessException_if_author_exist() {
        assertThrows(DataAccessException.class,
                () -> authorDao.add(new Author("Stan Lee")));
    }

    @DisplayName("Get all authors from testDB")
    @Test
    void should_return_all_authors() {
        assertEquals(3, authorDao.getAll().size());
    }

    @DisplayName("Get author by id from testDB")
    @Test
    void should_return_author() {
        Author author = authorDao.getById(1);
        assertEquals(1, author.getId());
        assertEquals("Stan Lee", author.getName());
    }

    @DisplayName("Get nonexistent author by id from testDB")
    @Test
    void should_throw_DataAccessException_when_author_not_exist() {
        assertThrows(DataAccessException.class,
                () -> authorDao.getById(4));
    }

    @DisplayName("Remove author from testDB")
    @Test
    void should_remove_author() {
        Author author = authorDao.getById(1);
        authorDao.remove(author);
        assertEquals(2, authorDao.getAll().size());
    }

    @DisplayName("Count authors in testDB")
    @Test
    void count() {
        assertEquals(3, authorDao.count());
    }

}