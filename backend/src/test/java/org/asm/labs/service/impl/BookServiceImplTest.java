package org.asm.labs.service.impl;

import lombok.SneakyThrows;
import org.asm.labs.model.Author;
import org.asm.labs.model.Book;
import org.asm.labs.model.Genre;
import org.asm.labs.repository.BookRepository;
import org.asm.labs.service.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@DisplayName("Book Service test")
@SpringBootTest
class BookServiceImplTest {

    @Autowired
    private BookService bookService;
    
    @MockBean
    private BookRepository bookRepository;
    
    @MockBean
    private AuthorService authorService;
    
    @MockBean
    private GenreService genreService;
    
    @Captor
    private ArgumentCaptor<Book> bookArgumentCaptor;


    @DisplayName("Должен корректно сохранять всю информацию о книге")
    @Test
    @SneakyThrows
    void shouldSaveBookInfo() {
        String bookTitle = "Book Service #Test";
        String authorsNames = "Author Service #Test";
        String genre = "Genre Service #Test";
        when(authorService.findByAuthorName(authorsNames)).thenReturn(new Author("Author Service #Test"));
        when(genreService.findByGenreName(genre)).thenReturn(new Genre(0L, "Genre Service #Test"));
        bookService.save(bookTitle, authorsNames, genre);
        verify(bookRepository).save(bookArgumentCaptor.capture());
        assertThat(bookArgumentCaptor.getAllValues()).isNotNull();
        assertEquals("Book Service #Test", bookArgumentCaptor.getValue().getTitle());
    }

    @DisplayName("Должен выбросить исключение на добавление книги, если жанра не существует")
    @Test
    @SneakyThrows
    void shouldThrowGenreNotExistExceptionWhenGenreDoesntExist() {
        String bookTitle = "Book Service #Test";
        String authorsNames = "Author Service #Test";
        String genre = "Genre Service #Test";
        when(authorService.findByAuthorName(authorsNames)).thenReturn(new Author("Author Service #Test"));
        when(genreService.findByGenreName(genre)).thenThrow(new GenreNotExistException());
        assertThrows(GenreNotExistException.class,
                () -> bookService.save(bookTitle, authorsNames, genre));
    }

    @DisplayName("Должен выбросить исключение на добавление книги, автора не существует")
    @Test
    @SneakyThrows
    void shouldThrowAuthorNotExistExceptionWhenAuthorsDoesntExist() {
        String bookTitle = "Book Service #Test";
        String authorsNames = "Author Service #Test";
        String genre = "Genre Service #Test";
        when(authorService.findByAuthorName(authorsNames)).thenThrow(new AuthorNotExistException());
        when(genreService.findByGenreName(genre)).thenReturn(new Genre(0L, "Genre Service #Test"));
        assertThrows(AuthorNotExistException.class,
                () -> bookService.save(bookTitle, authorsNames, genre));
    }

    @DisplayName("Должен загружать список всех ниг с полной информацией о них")
    @Test
    void shouldReturnCorrectBooksListWithAllInfo() {
        Book book = new Book(
            "Book Service #Test",
            Collections.singleton(new Author("Author Service #Test")),
            new Genre(0L, "Genre Service #Test")
        );
        when(bookRepository.findAll()).thenReturn(Collections.singletonList(book));
        List<Book> books = bookService.findAll();
        assertThat(books).isNotNull();
        assertEquals("Book Service #Test", books.get(0).getTitle());
    }

    @DisplayName("Должен загружать информацию о нужной книги")
    @Test
    @SneakyThrows
    void shouldFindExpectedBookById() {
        Book book = new Book(0,
            "Book Service #Test",
            Collections.singleton(new Author("Author Service #Test")),
            new Genre(0L, "Genre Service #Test")
        );
        when(bookRepository.findById(0L)).thenReturn(Optional.of(book));
        Book actualBook = bookService.findById(0L);
        assertEquals("Book Service #Test", actualBook.getTitle());
        
    }

    @DisplayName("Должен выбрасывать исключение BookNotExistException, если книги не существует")
    @Test
    void shouldThrowBookNotExistExceptionWhenBookNotExist() {
        assertThrows(BookNotExistException.class,
                () -> bookService.findById(10));
    }

    @DisplayName("Должен удалить книгу")
    @Test
    @SneakyThrows
    void shouldRemoveBook() {
        Book book = new Book(0,
            "Book Service #Test",
            Collections.singleton(new Author("Author Service #Test")),
            new Genre(0L, "Genre Service #Test")
        );
        when(bookRepository.findById(0L)).thenReturn(Optional.of(book));
        bookService.remove(0L);
        verify(bookRepository).delete(bookArgumentCaptor.capture());
        assertThat(bookArgumentCaptor.getAllValues()).isNotNull();
        assertEquals("Book Service #Test", bookArgumentCaptor.getValue().getTitle());
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