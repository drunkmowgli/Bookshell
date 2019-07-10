package org.asm.labs.repository.impl;

import org.asm.labs.model.Author;
import org.asm.labs.model.Book;
import org.asm.labs.model.Genre;
import org.asm.labs.repository.BookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Collections;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;


@DisplayName("Book Repository test")
@DataJpaTest
class BookRepositoryTest {
    
    @Autowired
    BookRepository bookRepository;
    
    @Autowired
    TestEntityManager em;
    
    
    @DisplayName("Должен корректно сохранять всю информацию о книге")
    @Test
    void shouldSaveBookInfo() {
        Set<Author> authors = Collections.singleton((em.find(Author.class, 1L)));
        Genre genre = em.find(Genre.class, 1L);
        Book actualAuthor = new Book("Test book", authors, genre);
        bookRepository.save(actualAuthor);
        assertThat(actualAuthor.getId()).isGreaterThan(0);
        Book expectedAuthor = em.find(Book.class, actualAuthor.getId());
        assertThat(expectedAuthor).isNotNull().matches(s -> !s.getTitle().equals(""));
    }
    
    @DisplayName("Должен обновить информацию об книге")
    @Test
    void shouldUpdateAuthorInfo() {
        Set<Author> authors = Collections.singleton((em.find(Author.class, 1L)));
        Genre genre = em.find(Genre.class, 1L);
        Book updatedBook = new Book(1, "Updated Book", authors, genre);
        bookRepository.save(updatedBook);
        assertThat(updatedBook.getId()).isGreaterThan(0);
        Book actualBook = em.find(Book.class, updatedBook.getId());
        assertThat(actualBook).isNotNull()
                              .matches(s -> !s.getTitle().equals(""))
                              .matches(s -> s.getTitle().equals("Updated Book"));
    }
    
    @DisplayName("Должен вернуть все книги")
    @Test
    void shouldReturnAllBooks() {
        assertFalse(bookRepository.findAll().isEmpty());
    }
    
    @DisplayName("Должен вернуть конкретную книгу")
    @Test
    void shouldFindExpectedBookById() {
        Book actualBook = bookRepository.findById(1L).orElseThrow();
        Book expectedBook = em.find(Book.class, 1L);
        assertThat(actualBook).isEqualToComparingFieldByField(expectedBook);
        System.out.println(actualBook);
    }
    
    @DisplayName("Должен удалить книгу по id")
    @Test
    void shouldRemoveBook() {
        Book deletedBook = bookRepository.findById(1L).orElseThrow();
        bookRepository.delete(deletedBook);
        Book expectedBook = em.find(Book.class, deletedBook.getId());
        assertThat(expectedBook).isNull();
    }
    
    @DisplayName("Должен вернуть количество книг")
    @Test
    void count() {
        assertThat(bookRepository.count()).isNotNull();
    }
    
}