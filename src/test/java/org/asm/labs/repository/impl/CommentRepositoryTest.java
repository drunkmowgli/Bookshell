package org.asm.labs.repository.impl;

import org.asm.labs.model.Comment;
import org.asm.labs.repository.CommentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DisplayName("Comment Repository test")
@DataMongoTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @DisplayName("Должен корректно сохранять всю информацию о комментарии")
    @Test
    void shouldSaveCommentInfo() {
        Comment comment = new Comment("Comment Test");
        Comment actualComment = commentRepository.save(comment);
        assertThat(actualComment.getId()).isNotNull();
        assertThat(actualComment.getDescription()).isEqualTo(comment.getDescription());
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

    @DisplayName("Должен вернуть количество комментариев")
    @Test
    void count() {
        Comment comment = new Comment("Comment Test");
        commentRepository.save(comment);
        assertThat(commentRepository.count()).isNotNull();
    }

}