package org.asm.labs.repository.impl;

import org.asm.labs.entity.Author;
import org.asm.labs.repository.AuthorRepositoryJpa;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DisplayName("Author DAO/Repository test")
@DataJpaTest(properties = "spring.profiles.active=test")
@Import({AuthorRepositoryJpaImpl.class})
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class AuthorRepositoryJpaImplTest {

    @Autowired
    private AuthorRepositoryJpa authorRepositoryJpa;

    @Autowired
    private TestEntityManager em;


    @DisplayName("Должен корректно сохранять всю информацию об авторе")
    @Test
    void shouldSaveAuthorInfo() {
        Author author = new Author("Author DAO #Test");
        authorRepositoryJpa.save(author);
        assertThat(author.getId()).isGreaterThan(0);
        Author actualAuthor = em.find(Author.class, author.getId());
        assertThat(actualAuthor).isNotNull().matches(s -> !s.getName().equals(""));
    }

//    @DisplayName("Add new author to testDB for throwing Exception check")
//    @Test
//    void shouldThrowDataAccessExceptionIfAuthorExist() {
//        assertThrows(DataAccessException.class,
//                () -> authorRepositoryJpa.save(new Author("Stan Lee")));
//    }

    @DisplayName("Должен загружать список всех авторов с полной информацией о них")
    @Test
    void shouldReturnCorrectAuthorsListWithAllInfo() {
        List<Author> authors = authorRepositoryJpa.findAll();
        assertThat(authors).isNotNull().hasSize(3).allMatch(s -> !s.getName().equals(""));
    }

    @DisplayName("Должен загружать информацию о нужном авторе")
    @Test
    void shouldFindExpectedAuthorById() {
        Author actualAuthor = authorRepositoryJpa.findById(1);
        Author expectedAuthor = em.find(Author.class, 1);
        assertThat(actualAuthor).isEqualToComparingFieldByFieldRecursively(expectedAuthor);
    }

//    @DisplayName("Get nonexistent author by id from testDB")
//    @Test
//    void shouldThrowDataAccessExceptionWhenAuthorNotExist() {
//        assertThrows(DataAccessException.class,
//                () -> authorRepositoryJpa.findById(4));
//    }

    @DisplayName("Remove author from testDB")
    @Test
    void shouldRemoveAuthor() {
        Author author = authorRepositoryJpa.findById(1);
        authorRepositoryJpa.remove(author);
        assertEquals(2, authorRepositoryJpa.findAll().size());
    }
//
//    @DisplayName("Count authors in testDB")
//    @Test
//    void count() {
//        assertEquals(3, authorRepositoryJpa.count());
//    }

}