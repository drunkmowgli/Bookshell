package org.asm.labs.service.impl;

import org.asm.labs.service.CommentNotExistException;
import org.asm.labs.service.CommentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Comment Service test")
@DataJpaTest(properties = "spring.profiles.active=test")
@Import({CommentServiceImpl.class})
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class CommentServiceImplTest {

    @Autowired
    private CommentService commentService;


    @DisplayName("Должен корректно сохранять информацию о комментарии")
    @Test
    void shouldSaveCommentInfo() {
        long beforeInsert = commentService.findAll().size();
        commentService.save("Comment Service #Test", 1L);
        long afterInsert = commentService.findAll().size();
        assertThat(beforeInsert).isLessThan(afterInsert);
    }

    @DisplayName("Должен вернуть информацию о всех комментариях")
    @Test
    void shouldReturnCorrectCommentsListWithAllInfo() {
        assertFalse(commentService.findAll().isEmpty());
    }

    @DisplayName("Должен загружать информацию о нужном комментарии")
    @Test
    void shouldFindExpectedCommentById() throws CommentNotExistException {
        assertEquals(1, commentService.findById(1).getId());
        assertEquals("Excellent book", commentService.findById(1).getCommentDescription());
    }

    @DisplayName("Должен выбрасывать исключение CommentNotExistException, если комментария не существует")
    @Test
    void shouldThrowCommentNotExistExceptionWhenCommentNotExist() {
        assertThrows(CommentNotExistException.class,
                () -> commentService.findById(10));
    }

    @DisplayName("Должен удалять комментарий")
    @Test
    void shouldRemoveComment() throws CommentNotExistException {
        long beforeDelete = commentService.findAll().size();
        commentService.remove(1);
        long afterDelete = commentService.findAll().size();
        assertThat(afterDelete).isLessThan(beforeDelete);
    }

    @DisplayName("Должен выбрасывать исключение CommentNotExistException, если комментария не существует")
    @Test
    void shouldThrowCommentNotExistExceptionWhenCommentNotExistOnRemove() {
        assertThrows(CommentNotExistException.class,
                () -> commentService.remove(10));
    }

    @DisplayName("Должен вернуть количество комментариев")
    @Test
    void count() {
        assertThat(commentService.count()).isNotNull();
    }
}