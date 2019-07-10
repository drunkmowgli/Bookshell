package org.asm.labs.repository.impl;

import org.asm.labs.model.Comment;
import org.asm.labs.repository.CommentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DisplayName("Comment Repository test")
@DataJpaTest
class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    private TestEntityManager em;

    @DisplayName("Должен вернуть все комментарии")
    @Test
    void shouldReturnCorrectCommentsListWithAllInfo() {
        assertFalse(commentRepository.findAll().isEmpty());
    }

    @DisplayName("Должен корректно сохранять всю информацию о комментарии")
    @Test
    void shouldSaveCommentInfo() {
        Comment actualComment = new Comment("Test comment");
        commentRepository.save(actualComment);
        assertThat(actualComment.getId()).isGreaterThan(0);
        Comment expectedComment = em.find(Comment.class, actualComment.getId());
        assertThat(expectedComment).isNotNull()
                .matches(comment -> !comment.getCommentDescription().equals(""));
    }

    @DisplayName("Должен корректно обновить всю информацию о комментарии")
    @Test
    void shouldUpdateCommentInfo() {
        Comment actualComment = new Comment(1, "Test comment");
        commentRepository.save(actualComment);
        assertThat(actualComment.getId()).isGreaterThan(0);
        Comment expectedComment = em.find(Comment.class, actualComment.getId());
        assertThat(expectedComment).isNotNull()
                .matches(comment -> !comment.getCommentDescription().equals(""));
    }

    @DisplayName("Должен вернуть конкретный комментарий по ID")
    @Test
    void shouldReturnCorrectCommentById() {
        Comment actualComment = commentRepository.findById(1L).orElseThrow();
        Comment expectedComment = em.find(Comment.class, 1L);
        assertThat(actualComment).isEqualToComparingFieldByFieldRecursively(expectedComment);
    }

    @DisplayName("Должен удалить комментарий")
    @Test
    void shouldRemoveComment() {
        Comment deletedComment = commentRepository.findById(1L).orElseThrow();
        commentRepository.delete(deletedComment);
        Comment expectedComment = em.find(Comment.class, deletedComment.getId());
        assertThat(expectedComment).isNull();
    }

    @DisplayName("Должен вернуть количество комментариев")
    @Test
    void shouldCountComments() {
        assertThat(commentRepository.count()).isNotNull();
    }
}