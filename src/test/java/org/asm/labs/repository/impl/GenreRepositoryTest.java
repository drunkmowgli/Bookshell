package org.asm.labs.repository.impl;

import org.asm.labs.model.Author;
import org.asm.labs.model.Book;
import org.asm.labs.model.Genre;
import org.asm.labs.repository.BookRepository;
import org.asm.labs.repository.GenreRepository;
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


@DisplayName("Genre Repository test")
@DataMongoTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ComponentScan("org.asm.labs.events")
class GenreRepositoryTest {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private BookRepository bookRepository;

    @DisplayName("Должен корректно сохранять всю информацию о жанре")
    @Test
    void shouldSaveGenreInfo() {
        Genre genre = new Genre("Genre Test");
        Genre actualGenre = genreRepository.save(genre);
        assertThat(actualGenre.getId()).isNotNull();
        assertThat(actualGenre.getName()).isEqualTo(genre.getName());
    }


    @DisplayName("Должен загружать список всех жанров с полной информацией о них")
    @Test
    void shouldReturnCorrectGenreListWithAllInfo() {
        List<Genre> genres = genreRepository.findAll();
        assertThat(genres.size()).isEqualTo(0);
    }


    @DisplayName("Должен загружать информацию о нужном жанре")
    @Test
    void shouldFindExpectedGenreById() {
        Genre genre = new Genre("Genre Test");
        genreRepository.save(genre);
        Genre actualGenre = genreRepository.findById(genre.getId()).orElseThrow();
        assertThat(actualGenre.getId()).isEqualTo(genre.getId());
        assertThat(actualGenre.getName()).isEqualTo(genre.getName());
    }

    @DisplayName("Должен выбросить исключение NoSuchElementException если жанр не существует")
    @Test
    void shouldThrowNoSuchElementExceptionIfGenreNotExist() {
        assertThrows(NoSuchElementException.class, () -> genreRepository.findById("123").orElseThrow());
    }

    @DisplayName("Должен удалять жанр")
    @Test
    void shouldRemoveGenre() {
        Genre genre = new Genre("Genre Test");
        genreRepository.save(genre);
        genreRepository.delete(genre);
        assertThat(genreRepository.findAll().size()).isEqualTo(0);
    }

    @DisplayName("При удалении жанра должен удалять его из книги")
    @Test
    void shouldRemoveGenreFromBookWhenRemovingGenre() {
        Book book = new Book("Book Test",
                Collections.singleton(new Author("Author Test")),
                new Genre("Genre Test"));
        bookRepository.save(book);
        Genre genre = genreRepository.findAll().get(0);
        genreRepository.delete(genre);
        assertThat(bookRepository.findAll().get(0).getGenre()).isNull();

    }

    @DisplayName("Должен вернуть количество жанров")
    @Test
    void count() {
        Genre genre = new Genre("Genre Test");
        genreRepository.save(genre);
        assertThat(genreRepository.count()).isNotNull();
    }

}