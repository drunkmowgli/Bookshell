package org.asm.labs.repository.impl;

import org.asm.labs.model.Author;
import org.asm.labs.model.Book;
import org.asm.labs.model.Genre;
import org.asm.labs.repository.AuthorRepository;
import org.asm.labs.repository.BookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DisplayName("Author Repository test")
@DataMongoTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ComponentScan("org.asm.labs.events")
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @DisplayName("Должен корректно сохранять всю информацию об авторе")
    @Test
    void shouldSaveAuthorInfo() {
        Author author = new Author("Author Test");
        Author actualAuthor = authorRepository.save(author);
        assertThat(actualAuthor.getId()).isNotNull();
        assertThat(actualAuthor.getName()).isEqualTo(author.getName());
    }


    @DisplayName("Должен загружать список всех авторов с полной информацией о них")
    @Test
    void shouldReturnCorrectAuthorsListWithAllInfo() {
        List<Author> authors = authorRepository.findAll();
        assertThat(authors.size()).isEqualTo(0);
    }


    @DisplayName("Должен загружать информацию о нужном авторе")
    @Test
    void shouldFindExpectedAuthorById() {
        Author author = new Author("Author Test");
        authorRepository.save(author);
        Author actualAuthor = authorRepository.findById(author.getId()).orElseThrow();
        assertThat(actualAuthor.getId()).isEqualTo(author.getId());
        assertThat(actualAuthor.getName()).isEqualTo(author.getName());
    }

    @DisplayName("Должен выбросить исключение NoSuchElementException если автора не существует")
    @Test
    void shouldThrowNoSuchElementExceptionIfAuthorNotExist() {
        assertThrows(NoSuchElementException.class, () -> authorRepository.findById("123").orElseThrow());
    }

    @DisplayName("Должен удалять автора")
    @Test
    void shouldRemoveAuthor() {
        Author author = new Author("Author Test");
        authorRepository.save(author);
        authorRepository.delete(author);
        assertThat(authorRepository.findAll().size()).isEqualTo(0);
    }

    @DisplayName("При удалении автора должен удалять его из книги")
    @Test
    void shouldRemoveAuthorFromBookWhenRemovingAuthor() {
        Set<Author> authors = new HashSet<>();
        authors.add(new Author("Author Test"));
        authors.add(new Author("New Author Test"));
        Book book = new Book("Book Test", authors, new Genre("Genre Test"));
        bookRepository.save(book);
        Author author = authorRepository.findAll().get(0);
        Set<Author> authorSetBeforeDelete = bookRepository.findAll().get(0).getAuthors();
        authorRepository.delete(author);
        Set<Author> authorSetAfterDelete = bookRepository.findAll().get(0).getAuthors();
        assertThat(authorSetBeforeDelete.size()).isGreaterThan(authorSetAfterDelete.size());
    }

    @DisplayName("Должен вернуть количество авторов")
    @Test
    void count() {
        Author author = new Author("Author Test");
        authorRepository.save(author);
        assertThat(authorRepository.count()).isNotNull();
    }

}