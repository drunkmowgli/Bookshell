package org.asm.labs.repository.impl;

import org.asm.labs.entity.Author;
import org.asm.labs.repository.AuthorRepositoryJpa;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.sound.midi.Soundbank;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DisplayName("Author DAO/Repository test")
@DataJpaTest(properties = "spring.profiles.active=test")
@Import({AuthorRepositoryJpaImpl.class})
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class AuthorRepositoryJpaImplTest {
    
    @Autowired
    private AuthorRepositoryJpa authorRepositoryJpa;
    
    @Autowired
    private TestEntityManager em;
    
    
    @DisplayName("Должен корректно сохранять всю информацию об авторе")
    @Test
    void shouldSaveAuthorInfo() {
        Author actualAuthor = new Author("Author Test");
        authorRepositoryJpa.save(actualAuthor);
        assertThat(actualAuthor.getId()).isGreaterThan(0);
        Author expectedAuthor = em.find(Author.class, actualAuthor.getId());
        assertThat(expectedAuthor).isNotNull().matches(s -> !s.getName().equals(""));
    }
    
    @DisplayName("Должен обновить информацию об Авторе")
    @Test
    void shouldUpdateAuthorInfo() {
        Author updatedAuthor = new Author(1, "Update Author");
        authorRepositoryJpa.save(updatedAuthor);
        assertThat(updatedAuthor.getId()).isGreaterThan(0);
        Author actualAuthor = em.find(Author.class, updatedAuthor.getId());
        assertThat(actualAuthor).isNotNull()
                                .matches(s -> !s.getName().equals(""))
                                .matches(s -> s.getName().equals("Update Author"));
    }
    
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
    
    @DisplayName("Должен выбрасывать исключение NoResultException, если автора не существует")
    @Test
    void shouldThrowNoResultExceptionWhenAuthorNotExist() {
        assertThrows(NoResultException.class,
            () -> authorRepositoryJpa.findById(4));
    }
    
    @DisplayName("Должен удалять автора")
    @Test
    void shouldRemoveAuthor() {
        Author deletedAuthor = authorRepositoryJpa.findById(1);
        authorRepositoryJpa.remove(deletedAuthor);
        Author expectedAuthor = em.find(Author.class, deletedAuthor.getId());
        assertThat(expectedAuthor).isNull();
    }
    
    @DisplayName("Должен вернуть количество авторов")
    @Test
    void count() {
        assertEquals(3, authorRepositoryJpa.count());
    }
    
}