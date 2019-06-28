package org.asm.labs.repository.impl;

import org.asm.labs.entity.Author;
import org.asm.labs.entity.Book;
import org.asm.labs.entity.Genre;
import org.asm.labs.repository.AuthorRepositoryJpa;
import org.asm.labs.repository.BookRepositoryJpa;
import org.asm.labs.repository.GenreRepositoryJpa;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.Collections;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DisplayName("Book Repository test")
@DataJpaTest(properties = "spring.profiles.active=test")
@Import({BookRepositoryJpaImpl.class, GenreRepositoryJpaImpl.class, AuthorRepositoryJpaImpl.class})
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class BookRepositoryJpaImplTest {

    @Autowired
    AuthorRepositoryJpa authorRepositoryJpa;

    @Autowired
    GenreRepositoryJpa genreRepositoryJpa;

    @Autowired
    BookRepositoryJpa bookRepositoryJpa;

    @Autowired
    TestEntityManager em;


    @DisplayName("Должен корректно сохранять всю информацию о книге")
    @Test
    void shouldSaveBookInfo() {
        Set<Author> authors = Collections.singleton((authorRepositoryJpa.findById(1)));
        Genre genre = genreRepositoryJpa.findById(1);
        Book actualAuthor = new Book("Test book", authors, genre);
        bookRepositoryJpa.save(actualAuthor);
        assertThat(actualAuthor.getId()).isGreaterThan(0);
        Book expectedAuthor = em.find(Book.class, actualAuthor.getId());
        assertThat(expectedAuthor).isNotNull().matches(s -> !s.getTitle().equals(""));
    }

    @DisplayName("Должен обновить информацию об книге")
    @Test
    void shouldUpdateAuthorInfo() {
        Set<Author> authors = Collections.singleton((authorRepositoryJpa.findById(1)));
        Genre genre = genreRepositoryJpa.findById(1);
        Book updatedBook = new Book(1, "Updated Book", authors, genre);
        bookRepositoryJpa.save(updatedBook);
        assertThat(updatedBook.getId()).isGreaterThan(0);
        Book actualBook = em.find(Book.class, updatedBook.getId());
        assertThat(actualBook).isNotNull()
                .matches(s -> !s.getTitle().equals(""))
                .matches(s -> s.getTitle().equals("Updated Book"));
    }

    @DisplayName("Должен вернуть все книги")
    @Test
    void shouldReturnAllBooks() {
        assertEquals(5, bookRepositoryJpa.findAll().size());
    }

    @DisplayName("Должен вернуть конкретную книгу")
    @Test
    void shouldFindExpectedBookById() {
        Book actualBook = bookRepositoryJpa.findById(1);
        Book expectedBook = em.find(Book.class, 1);
        assertThat(actualBook).isEqualToComparingFieldByField(expectedBook);
        System.out.println(actualBook);
    }

    @DisplayName("Должен выбросить исключение NoResultException")
    @Test
    void shouldThrowNoResultExceptionIfBookNotExist() {
        assertThrows(NoResultException.class, () -> bookRepositoryJpa.findById(10));
    }

    @DisplayName("Должен удалить книгу по id")
    @Test
    void shouldRemoveBook() {
        Book deletedBook = bookRepositoryJpa.findById(1);
        bookRepositoryJpa.remove(deletedBook);
        Book expectedBook = em.find(Book.class, deletedBook.getId());
        assertThat(expectedBook).isNull();
    }

    @DisplayName("Должен вернуть количество книг")
    @Test
    void count() {
        assertEquals(5, bookRepositoryJpa.count());
    }

}