package org.asm.labs.service.impl;

import org.asm.labs.model.Comment;
import org.asm.labs.service.CommentNotExistException;
import org.asm.labs.service.CommentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Comment Service test")
@DataMongoTest
@Import({CommentServiceImpl.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class CommentServiceImplTest {

    @Autowired
    private CommentService commentService;


    @DisplayName("Должен корректно сохранять информацию о комментарии")
    @Test
    void shouldSaveCommentInfo() {
        long beforeInsert = commentService.findAll().size();
        commentService.save(new Comment("Comment Service #Test"));
        long afterInsert = commentService.findAll().size();
        assertThat(beforeInsert).isLessThan(afterInsert);
    }

    @DisplayName("Должен вернуть информацию о всех комментариях")
    @Test
    void shouldReturnCorrectCommentsListWithAllInfo() {
        assertTrue(commentService.findAll().isEmpty());
    }

    @DisplayName("Должен загружать информацию о нужном комментарии")
    @Test
    void shouldFindExpectedCommentById() throws CommentNotExistException {
        commentService.save(new Comment("Comment Service #Test"));
        String commentId = commentService.findAll().get(0).getId();
        assertThat(commentService.findById(commentId)).isNotNull();
        assertEquals("Comment Service #Test", commentService.findById(commentId).getDescription());
    }

    @DisplayName("Должен выбрасывать исключение CommentNotExistException, если комментария не существует")
    @Test
    void shouldThrowCommentNotExistExceptionWhenCommentNotExist() {
        assertThrows(CommentNotExistException.class,
                () -> commentService.findById("123"));
    }

    @DisplayName("Должен удалять комментарий")
    @Test
    void shouldRemoveComment() throws CommentNotExistException {
        commentService.save(new Comment("Comment Service #Test"));
        String commentId = commentService.findAll().get(0).getId();
        long beforeDelete = commentService.findAll().size();
        commentService.remove(commentId);
        long afterDelete = commentService.findAll().size();
        assertThat(afterDelete).isLessThan(beforeDelete);
    }

    @DisplayName("Должен выбрасывать исключение CommentNotExistException, если комментария не существует")
    @Test
    void shouldThrowCommentNotExistExceptionWhenCommentNotExistOnRemove() {
        assertThrows(CommentNotExistException.class,
                () -> commentService.remove("123"));
    }

    @DisplayName("Должен вернуть количество комментариев")
    @Test
    void count() {
        assertThat(commentService.count()).isNotNull();
    }
}