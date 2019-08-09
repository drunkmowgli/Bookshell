package org.asm.labs.repository.impl;

import org.asm.labs.model.Author;
import org.asm.labs.model.Book;
import org.asm.labs.model.Genre;
import org.asm.labs.repository.BookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DisplayName("Book Repository test")
@DataMongoTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ComponentScan("org.asm.labs.events")
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;


    @DisplayName("Должен корректно сохранять всю информацию о книге")
    @Test
    void shouldSaveBookInfo() {
        Book book = new Book("Book Test", Collections.singleton(new Author("Author Test")),
                new Genre("Genre Test"));
        Book actualBook = bookRepository.save(book);
        assertThat(actualBook.getId()).isNotNull();
        assertThat(actualBook.getTitle()).isEqualTo(book.getTitle());
    }


    @DisplayName("Должен загружать список всех книг с полной информацией о них")
    @Test
    void shouldReturnCorrectBookListWithAllInfo() {
        List<Book> books = bookRepository.findAll();
        assertThat(books.size()).isEqualTo(0);
    }


    @DisplayName("Должен загружать информацию о нужной книге")
    @Test
    void shouldFindExpectedBookById() {
        Book book = new Book("Book Test", Collections.singleton(new Author("Author Test")),
                new Genre("Genre Test"));
        bookRepository.save(book);
        Book actualBook = bookRepository.findById(book.getId()).orElseThrow();
        assertThat(actualBook.getId()).isEqualTo(book.getId());
        assertThat(actualBook.getTitle()).isEqualTo(book.getTitle());
    }

    @DisplayName("Должен выбросить исключение NoSuchElementException если книги не существует")
    @Test
    void shouldThrowNoSuchElementExceptionIfBookNotExist() {
        assertThrows(NoSuchElementException.class, () -> bookRepository.findById("123").orElseThrow());
    }

    @DisplayName("Должен удалять книгу")
    @Test
    void shouldRemoveBook() {
        Book book = new Book("Book Test", Collections.singleton(new Author("Author Test")),
                new Genre("Genre Test"));
        bookRepository.save(book);
        bookRepository.delete(book);
        assertThat(bookRepository.findAll().size()).isEqualTo(0);
    }

    @DisplayName("Должен вернуть количество книг")
    @Test
    void count() {
        Book book = new Book("Book Test", Collections.singleton(new Author("Author Test")),
                new Genre("Genre Test"));
        bookRepository.save(book);
        assertThat(bookRepository.count()).isNotNull();
    }

}