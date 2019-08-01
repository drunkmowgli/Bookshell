package org.asm.labs.service.impl;

import lombok.SneakyThrows;
import org.asm.labs.model.Author;
import org.asm.labs.model.Book;
import org.asm.labs.model.Comment;
import org.asm.labs.model.Genre;
import org.asm.labs.repository.BookRepository;
import org.asm.labs.repository.CommentRepository;
import org.asm.labs.service.CommentNotExistException;
import org.asm.labs.service.CommentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@DisplayName("Comment Service test")
@DataJpaTest
@Import({CommentServiceImpl.class})
class CommentServiceImplTest {

    @Autowired
    private CommentService commentService;

    @MockBean
    private CommentRepository commentRepository;

    @MockBean
    private BookRepository bookRepository;

    @Captor
    ArgumentCaptor<Comment> commentArgumentCaptor;
    

    @DisplayName("Должен корректно сохранять информацию о комментарии")
    @Test
    void shouldSaveCommentInfo() {
        when(bookRepository.findById(0L)).thenReturn(Optional.of(new Book(
                0L,
                "Comment Service #Test",
                Collections.singleton(new Author("Comment Service #Test")),
                new Genre(0, "Comment Service #Test")
        )));
        commentService.save("Comment Service #Test", 0L);
        verify(commentRepository).save(commentArgumentCaptor.capture());
        assertThat(commentArgumentCaptor).isNotNull();
        assertEquals("Comment Service #Test", commentArgumentCaptor.getValue().getCommentDescription());
    }

    @DisplayName("Должен вернуть информацию о всех комментариях")
    @Test
    void shouldReturnCorrectCommentsListWithAllInfo() {
        when(commentService.findAll()).thenReturn(Collections.singletonList(
                new Comment("Comment Service #Test"))
        );
        assertThat(commentService.findAll()).isNotNull();
    }

    @DisplayName("Должен загружать информацию о нужном комментарии")
    @Test
    @SneakyThrows
    void shouldFindExpectedCommentById() {
        when(commentRepository.findById(0L)).thenReturn(Optional.of(new Comment("Comment Service #Test")));
        Comment comment = commentService.findById(0L);
        assertEquals("Comment Service #Test", comment.getCommentDescription());
    }

    @DisplayName("Должен вернуть список комментариев для конкретной книги")
    @Test
    void shouldReturnCommentListByBookId() {
        when(commentService.findByBookId(0)).thenReturn(Collections.singletonList(
                new Comment("Comment Service #Test")));
        List<Comment> actualCommentList = commentService.findByBookId(0);
        assertEquals("Comment Service #Test", actualCommentList.get(0).getCommentDescription());
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
        when(commentRepository.findById(0L)).thenReturn(Optional.of(new Comment("Comment Service #Test")));
        commentService.remove(0L);
        verify(commentRepository).delete(commentArgumentCaptor.capture());
        assertEquals(0L, commentArgumentCaptor.getValue().getId());
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