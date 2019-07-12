package org.asm.labs.repository.impl;

import org.asm.labs.model.Author;
import org.asm.labs.model.Book;
import org.asm.labs.model.Comment;
import org.asm.labs.model.Genre;
import org.asm.labs.repository.BookRepository;
import org.asm.labs.repository.CommentRepository;
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


@DisplayName("Comment Repository test")
@DataMongoTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ComponentScan("org.asm.labs.events")
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BookRepository bookRepository;

    @DisplayName("Должен корректно сохранять всю информацию о комментарии")
    @Test
    void shouldSaveCommentInfo() {
        Book book = new Book(
                "Book Test",
                Collections.singleton(new Author("Author Test")),
                new Genre("Genre Test")
        );
        bookRepository.save(book);
        Book repoBook = bookRepository.findAll().get(0);
        Comment comment = new Comment("Comment Test", repoBook);
        Comment actualComment = commentRepository.save(comment);
        assertThat(actualComment.getId()).isNotNull();
        assertThat(actualComment.getDescription()).isEqualTo(comment.getDescription());
        System.out.println(commentRepository.findAll());
    }


    @DisplayName("Должен загружать список всех комментариях с полной информацией о них")
    @Test
    void shouldReturnCorrectCommentListWithAllInfo() {
        List<Comment> comments = commentRepository.findAll();
        assertThat(comments.size()).isEqualTo(0);
    }


    @DisplayName("Должен загружать информацию о нужном комментарии")
    @Test
    void shouldFindExpectedCommentById() {
        Comment comment = new Comment("Comment Test");
        commentRepository.save(comment);
        Comment actualComment = commentRepository.findById(comment.getId()).orElseThrow();
        assertThat(actualComment.getId()).isEqualTo(comment.getId());
        assertThat(actualComment.getDescription()).isEqualTo(comment.getDescription());
    }

    @DisplayName("Должен выбросить исключение NoSuchElementException если комментария не существует")
    @Test
    void shouldThrowNoSuchElementExceptionIfCommentNotExist() {
        assertThrows(NoSuchElementException.class, () -> commentRepository.findById("123").orElseThrow());
    }

    @DisplayName("Должен удалять комментарий")
    @Test
    void shouldRemoveComment() {
        Comment comment = new Comment("Comment Test");
        commentRepository.save(comment);
        commentRepository.delete(comment);
        assertThat(commentRepository.findAll().size()).isEqualTo(0);
    }

    @DisplayName("При удалении книги, должен удалять комментарий")
    @Test
    void shouldRemoveBookFromCommentWhenRemovingBook() {
        Book bookOne = new Book(
                "Book Test 1",
                Collections.singleton(new Author("Author Test 1")),
                new Genre("Genre Test 1")
        );
        Book bookTwo = new Book(
                "Book Test 2",
                Collections.singleton(new Author("Author Test 2")),
                new Genre("Genre Test 2")
        );
        bookRepository.save(bookOne);
        bookRepository.save(bookTwo);
        Comment commentOne = new Comment("Comment Test One", bookRepository.findAll().get(0));
        Comment commentTwo = new Comment("Comment Test Two", bookRepository.findAll().get(1));
        commentRepository.save(commentOne);
        commentRepository.save(commentTwo);
        Book deletingBook = bookRepository.findAll().get(0);
        long bookRepoSizeBeforeDelete = bookRepository.findAll().size();
        bookRepository.delete(deletingBook);
        long bookRepoSizeAfterDelete = bookRepository.findAll().size();
        assertThat(bookRepoSizeAfterDelete).isLessThan(bookRepoSizeBeforeDelete);
    }

    @DisplayName("Должен вернуть количество комментариев")
    @Test
    void count() {
        Comment comment = new Comment("Comment Test");
        commentRepository.save(comment);
        assertThat(commentRepository.count()).isNotNull();
    }

}