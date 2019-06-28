package org.asm.labs.repository.impl;

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

import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("Book DAO/Repository test")
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


//    @DisplayName("Add new book to testDB")
//    @Test
//    void should6BooksWhenAdded() {
//        List<Author> authors = Collections.singletonList(authorRepositoryJpa.findById(1));
//        Book book = new Book("Test book from BookRepositoryJpa", authors, genreRepositoryJpa.findById(1));
//        System.out.println(authors.toString());
//        bookRepositoryJpa.add(book);
//        assertFalse(bookRepositoryJpa.findAll().isEmpty());
//    }

//    @DisplayName("Add new book to testDB for throwing Exception check")
//    @Test
//    void shouldThrowDataAccessExceptionIfBookExist() {
//        Book existBook = bookRepositoryJpa.findById(1).orElseThrow();
//        assertThrows(DataAccessException.class,
//                () -> bookRepositoryJpa.add(existBook));
//    }

    @DisplayName("Должен вернуть все книги")
    @Test
    void shouldReturnAllBooks() {
        assertEquals(5, bookRepositoryJpa.findAll().size());
    }

//    @DisplayName("Get book by id from testDB")
//    @Test
//    void shouldReturnBook() {
//        Book book = bookRepositoryJpa.findById(1).orElseThrow();
//        assertEquals(1, book.getId());
//        assertEquals("Spider-Man #1", book.getTitle());
//        assertEquals("Comics", book.getGenre().getGenreName());
//    }

//    @DisplayName("Get book by id from testDB")
//    @Test
//    void shouldReturnEmptyWhenBookNotExist() {
//        assertFalse(bookRepositoryJpa.findById(10).isPresent());
//    }

//    @DisplayName("Remove book from testDB")
//    @Test
//    void remove() {
//        Book book = bookRepositoryJpa.findById(1).orElseThrow();
//        bookRepositoryJpa.remove(book);
//        assertEquals(4, bookRepositoryJpa.findAll().size());
//    }
//
//    @DisplayName("Count books in testDB")
//    @Test
//    void count() {
//        assertEquals(5, bookRepositoryJpa.count());
//    }

}