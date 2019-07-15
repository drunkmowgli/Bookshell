package org.asm.labs.service.impl;

import lombok.SneakyThrows;
import org.asm.labs.model.Author;
import org.asm.labs.model.Book;
import org.asm.labs.model.Genre;
import org.asm.labs.repository.BookRepository;
import org.asm.labs.service.BookNotExistException;
import org.asm.labs.service.BookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@DisplayName("Book Service test")
@SpringBootTest(properties = {
    InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
    ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
class BookServiceImplTest {

    @Autowired
    BookService bookService;

    @MockBean
    BookRepository bookRepository;
    
    @Captor
    ArgumentCaptor<Book> captor;


    @DisplayName("Должен корректно сохранять всю информацию о книге")
    @Test
    void shouldSaveBookInfo() {
        bookService.save("Book Service #Test",
            "Author Service #Test",
            "Genre Service #Test"
            );
        verify(bookRepository).save(captor.capture());
        assertEquals("Book Service #Test", captor.getValue().getTitle());
    }


    @DisplayName("Должен загружать список всех книг с полной информацией о них")
    @Test
    void shouldReturnCorrectBooksListWithAllInfo() {
        assertTrue(bookService.findAll().isEmpty());
    }

    @DisplayName("Должен загружать информацию о нужной книги")
    @Test
    @SneakyThrows
    void shouldFindExpectedBookById() {
        when(bookRepository.findById("1234567890")).thenReturn(Optional.of(
            new Book(
                "Book Service #Test",
                Collections.singleton(new Author("Author Service #Test")),
                new Genre("Genre Service #Test")
            )
        ));
        String actualBookTitle = bookRepository.findById("1234567890").orElseThrow().getTitle();
        assertEquals("Book Service #Test", actualBookTitle);
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
        when(bookRepository.findById("1234567890")).thenReturn(Optional.of(
            new Book(
                "Book Service #Test",
                Collections.singleton(new Author("Author Service #Test")),
                new Genre("Genre Service #Test")
            )
        ));
        bookService.remove("1234567890");
        verify(bookRepository).delete(captor.capture());
        assertEquals("Book Service #Test", captor.getValue().getTitle());
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