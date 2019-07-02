package org.asm.labs.repository.impl;

import org.asm.labs.entity.Comment;
import org.asm.labs.repository.CommentRepositoryJpa;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Comment Repository test")
@DataJpaTest(properties = "spring.profiles.active=test")
@Import({CommentRepositoryJpaImpl.class})
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class CommentRepositoryJpaImplTest {

    @Autowired
    CommentRepositoryJpa commentRepositoryJpa;

    @Autowired
    private TestEntityManager em;

    @DisplayName("Должен вернуть все комментарии")
    @Test
    void shouldReturnCorrectCommentsListWithAllInfo() {
        assertEquals(5, commentRepositoryJpa.findAll().size());
    }

    @DisplayName("Должен корректно сохранять всю информацию о комментарии")
    @Test
    void shouldSaveCommentInfo() {
        Comment actualComment = new Comment("Test comment");
        commentRepositoryJpa.save(actualComment);
        assertThat(actualComment.getId()).isGreaterThan(0);
        Comment expectedComment = em.find(Comment.class, actualComment.getId());
        assertThat(expectedComment).isNotNull()
                .matches(comment -> !comment.getCommentDescription().equals(""));
    }

    @DisplayName("Должен корректно обновить всю информацию о комментарии")
    @Test
    void shouldUpdateCommentInfo() {
        Comment actualComment = new Comment(1, "Test comment");
        commentRepositoryJpa.save(actualComment);
        assertThat(actualComment.getId()).isGreaterThan(0);
        Comment expectedComment = em.find(Comment.class, actualComment.getId());
        assertThat(expectedComment).isNotNull()
                .matches(comment -> !comment.getCommentDescription().equals(""));
    }

    @DisplayName("Должен вернуть конкретный комментарий по ID")
    @Test
    void shouldReturnCorrectCommentById() {
        Comment actualComment = commentRepositoryJpa.findById(1);
        Comment expectedComment = em.find(Comment.class, 1);
        assertThat(actualComment).isEqualToComparingFieldByFieldRecursively(expectedComment);
    }

    @DisplayName("Должен выбрасывать исключение NoResultException, если автора не существует")
    @Test
    void shouldThrowNoResultExceptionWhenCommentNotExist() {
        assertThrows(NoResultException.class,
                () -> commentRepositoryJpa.findById(10));
    }

    @DisplayName("Должен удалить комментарий")
    @Test
    void shouldRemoveComment() {
        Comment deletedComment = commentRepositoryJpa.findById(1);
        commentRepositoryJpa.remove(deletedComment);
        Comment expectedComment = em.find(Comment.class, deletedComment.getId());
        assertThat(expectedComment).isNull();
    }

    @DisplayName("Должен вернуть количество комментариев")
    @Test
    void shouldCountComments() {
        assertEquals(5, commentRepositoryJpa.count());
    }
}