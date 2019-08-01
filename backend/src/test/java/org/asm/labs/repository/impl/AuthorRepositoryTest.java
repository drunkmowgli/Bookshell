package org.asm.labs.repository.impl;

import org.asm.labs.model.Author;
import org.asm.labs.repository.AuthorRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;


@DisplayName("Author Repository test")
@DataJpaTest
@Transactional
@AutoConfigureTestDatabase(replace = NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private TestEntityManager em;


    @DisplayName("Должен корректно сохранять всю информацию об авторе")
    @Test
    void shouldSaveAuthorInfo() {
        Author actualAuthor = new Author("Author Test");
        authorRepository.save(actualAuthor);
        assertThat(actualAuthor.getId()).isGreaterThan(0);
        Author expectedAuthor = em.find(Author.class, actualAuthor.getId());
        assertThat(expectedAuthor).isNotNull().matches(author -> !author.getName().equals(""));
    }

    @DisplayName("Должен обновить информацию об Авторе")
    @Test
    void shouldUpdateAuthorInfo() {
        Author updatedAuthor = new Author(1, "Update Author");
        authorRepository.save(updatedAuthor);
        assertThat(updatedAuthor.getId()).isGreaterThan(0);
        Author actualAuthor = em.find(Author.class, updatedAuthor.getId());
        assertThat(actualAuthor).isNotNull()
                .matches(author -> !author.getName().equals(""))
                .matches(author -> author.getName().equals("Update Author"));
    }

    @DisplayName("Должен загружать список всех авторов с полной информацией о них")
    @Test
    void shouldReturnCorrectAuthorsListWithAllInfo() {
        List<Author> authors = authorRepository.findAll();
        assertThat(authors).isNotNull().allMatch(author -> !author.getName().equals(""));
    }

    @DisplayName("Должен загружать информацию о нужном авторе по ID")
    @Test
    void shouldFindExpectedAuthorById() {
        Author actualAuthor = authorRepository.findById(1L).orElseThrow();
        Author expectedAuthor = em.find(Author.class, 1L);
        assertThat(actualAuthor).isEqualToComparingFieldByFieldRecursively(expectedAuthor);
    }

    @DisplayName("Должен загружать информацию о нужном авторе по имени")
    @Test
    void shouldFindExpectedAuthorByName() {
        Author actualAuthor = authorRepository.findByName("Stan Lee").orElseThrow();
        Author expectedAuthor = em.find(Author.class, 1L);
        assertThat(actualAuthor).isEqualToComparingFieldByFieldRecursively(expectedAuthor);
    }

    @DisplayName("Должен удалять автора")
    @Test
    void shouldRemoveAuthor() {
        Author deletedAuthor = authorRepository.findById(1L).orElseThrow();
        authorRepository.delete(deletedAuthor);
        Author expectedAuthor = em.find(Author.class, deletedAuthor.getId());
        assertThat(expectedAuthor).isNull();
    }

    @DisplayName("Должен вернуть количество авторов")
    @Test
    void count() {
        assertThat(authorRepository.count()).isNotNull();
    }

}