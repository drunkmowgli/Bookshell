package org.asm.labs.service.impl;

import org.asm.labs.model.Author;
import org.asm.labs.service.AuthorNotExistException;
import org.asm.labs.service.AuthorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Author Service test")
@DataJpaTest
@Import({AuthorServiceImpl.class})
class AuthorServiceImplTest {

    @Autowired
    private AuthorService authorService;


    @DisplayName("Должен корректно сохранять информацию об авторе")
    @Test
    void shouldSaveAuthorInfo() {
        long beforeInsert = authorService.findAll().size();
        authorService.save(new Author("Author Service #Test"));
        long afterInsert = authorService.findAll().size();
        assertThat(afterInsert).isGreaterThan(beforeInsert);
    }

    @DisplayName("Должен вернуть информацию о всех авторах")
    @Test
    void shouldReturnCorrectAuthorsListWithAllInfo() {
        assertFalse(authorService.findAll().isEmpty());
    }

    @DisplayName("Должен загружать информацию о нужном авторе")
    @Test
    void shouldFindExpectedAuthorById() throws AuthorNotExistException {
        assertEquals(1, authorService.findById(1).getId());
        assertEquals("Stan Lee", authorService.findById(1).getName());
    }

    @DisplayName("Должен выбрасывать исключение AuthorNotExistException, если автора не существует")
    @Test
    void shouldThrowAuthorNotExistExceptionWhenAuthorNotExist() {
        assertThrows(AuthorNotExistException.class,
                () -> authorService.findById(10L));
    }

    @DisplayName("Должен удалять автора")
    @Test
    void shouldRemoveAuthor() throws AuthorNotExistException {
        long beforeDelete = authorService.findAll().size();
        authorService.delete(1);
        long afterDelete = authorService.findAll().size();
        assertThat(afterDelete).isLessThan(beforeDelete);
    }

    @DisplayName("Должен выбрасывать исключение AuthorNotExistException, если автора не существует")
    @Test
    void shouldThrowAuthorNotExistExceptionWhenAuthorNotExistOnRemove() {
        assertThrows(AuthorNotExistException.class,
                () -> authorService.delete(10L));
    }

    @DisplayName("Должен вернуть количество авторов")
    @Test
    void count() {
        assertThat(authorService.count()).isNotNull();
    }
}