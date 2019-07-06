package org.asm.labs.service.impl;

import org.asm.labs.service.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Book Service test")
@DataJpaTest(properties = "spring.profiles.active=test")
@Import({BookServiceImpl.class,
        AuthorServiceImpl.class,
        GenreServiceImpl.class})
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class BookServiceImplTest {

    @Autowired
    BookService bookService;

    @Autowired
    AuthorService authorService;

    @Autowired
    GenreService genreService;


    @DisplayName("Должен корректно сохранять всю информацию о книге")
    @Test
    void shouldSaveBookInfo() throws GenreNotExistException, AuthorNotExistException {
        String bookTitle = "Test title from BookService";
        String authorsIds = "1,2";
        int genreId = 1;
        long beforeInsert = bookService.findAll().size();
        bookService.save(bookTitle, authorsIds, genreId);
        long afterInsert = bookService.findAll().size();
        assertThat(afterInsert).isGreaterThan(beforeInsert);
    }

    @DisplayName("Должен выбросить исключение на добавление книги, если жанра не существует")
    @Test
    void shouldThrowGenreDoesntExistExceptionWhenGenreDoesntExist() {
        String bookTitle = "Test title from BookService";
        String authorsIds = "1,2";
        int genreId = 10;
        assertThrows(GenreNotExistException.class,
                () -> bookService.save(bookTitle, authorsIds, genreId));
    }

    @DisplayName("Должен выбросить исключение на добавление книги, автора не существует")
    @Test
    void shouldThrowAuthorDoesntExistExceptionWhenAuthorsDoesntExist() {
        String bookTitle = "Test title from BookService";
        String authorsIds = "10,2";
        int genreId = 1;
        assertThrows(AuthorNotExistException.class,
                () -> bookService.save(bookTitle, authorsIds, genreId));
    }

    @DisplayName("Должен загружать список всех ниг с полной информацией о них")
    @Test
    void shouldReturnCorrectBooksListWithAllInfo() {
        assertFalse(bookService.findAll().isEmpty());
    }

    @DisplayName("Должен загружать информацию о нужной книги")
    @Test
    void shouldFindExpectedBookById() throws BookNotExistException {
        assertEquals(2, bookService.findById(2).getId());
        assertEquals("Dark Horse Comics #1", bookService.findById(2).getTitle());
    }

    @DisplayName("Должен выбрасывать исключение NoResultException, если книги не существует")
    @Test
    void shouldThrowNoResultExceptionWhenBookNotExist() {
        assertThrows(BookNotExistException.class,
                () -> bookService.findById(10));
    }

    @DisplayName("Должен удалить книгу")
    @Test
    void shouldRemoveBook() throws BookNotExistException {
        long beforeDelete = bookService.findAll().size();
        bookService.remove(1);
        long afterDelete = bookService.findAll().size();
        assertThat(afterDelete).isLessThan(beforeDelete);
    }

    @DisplayName("Должен выбросить исключение на удалении книги, если такой книги не существует")
    @Test
    void shouldThrowBookDoesntExistExceptionOnDelete() {
        assertThrows(BookNotExistException.class,
                () -> bookService.remove(10));
    }

    @DisplayName("Должен вернуть количество всех книг")
    @Test
    void count() {
        assertThat(bookService.count()).isNotNull();
    }


}