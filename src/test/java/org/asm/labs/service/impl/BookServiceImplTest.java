package org.asm.labs.service.impl;

import org.asm.labs.model.Author;
import org.asm.labs.model.Book;
import org.asm.labs.model.Genre;
import org.asm.labs.service.AuthorService;
import org.asm.labs.service.BookNotExistException;
import org.asm.labs.service.BookService;
import org.asm.labs.service.GenreService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Collections;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Book Service test")
@DataMongoTest
@Import({BookServiceImpl.class,
    AuthorServiceImpl.class,
    GenreServiceImpl.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ComponentScan("org.asm.labs.events")
class BookServiceImplTest {
    
    @Autowired
    BookService bookService;
    
    @Autowired
    AuthorService authorService;
    
    @Autowired
    GenreService genreService;
    
    
    @DisplayName("Должен корректно сохранять всю информацию о книге")
    @Test
    void shouldSaveBookInfo() {
        long beforeInsert = bookService.findAll().size();
        Book book = new Book(
            "Book Service #Test",
            Collections.singleton(new Author("Author #Test")),
            new Genre("Genre #Test")
        );
        bookService.save(book);
        System.out.println(bookService.findAll());
        long afterInsert = bookService.findAll().size();
        assertThat(afterInsert).isGreaterThan(beforeInsert);
    }
    
    
    @DisplayName("Должен загружать список всех книг с полной информацией о них")
    @Test
    void shouldReturnCorrectBooksListWithAllInfo() {
        Book book = new Book(
            "Book Service #Test",
            Collections.singleton(new Author("Author #Test")),
            new Genre("Genre #Test")
        );
        bookService.save(book);
        assertFalse(bookService.findAll().isEmpty());
    }
    
    @DisplayName("Должен загружать информацию о нужной книги")
    @Test
    void shouldFindExpectedBookById() throws BookNotExistException {
        Book book = new Book(
            "Book Service #Test",
            Collections.singleton(new Author("Author #Test")),
            new Genre("Genre #Test")
        );
        bookService.save(book);
        String bookId = bookService.findAll().get(0).getId();
        assertThat(bookId).isNotNull();
        assertEquals("Book Service #Test", bookService.findById(bookId).getTitle());
    }
    
    @DisplayName("Должен выбрасывать исключение BookNotExistException, если книги не существует")
    @Test
    void shouldThrowBookNotExistExceptionWhenBookNotExist() {
        assertThrows(BookNotExistException.class,
            () -> bookService.findById("123"));
    }
    
    @DisplayName("Должен удалить книгу")
    @Test
    void shouldRemoveBook() throws BookNotExistException {
        Book book = new Book(
            "Book Service #Test",
            Collections.singleton(new Author("Author #Test")),
            new Genre("Genre #Test")
        );
        bookService.save(book);
        long beforeDelete = bookService.findAll().size();
        String bookId = bookService.findAll().get(0).getId();
        bookService.remove(bookId);
        long afterDelete = bookService.findAll().size();
        assertThat(afterDelete).isLessThan(beforeDelete);
    }
    
    @DisplayName("Должен выбросить исключение на удалении книги, если такой книги не существует")
    @Test
    void shouldThrowBookDoesntExistExceptionOnDelete() {
        assertThrows(BookNotExistException.class,
            () -> bookService.remove("123"));
    }
    
    @DisplayName("Должен вернуть количество всех книг")
    @Test
    void count() {
        assertThat(bookService.count()).isNotNull();
    }
    
    
}