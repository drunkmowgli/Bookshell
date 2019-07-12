package org.asm.labs.service.impl;

import org.asm.labs.service.AuthorNotExistException;
import org.asm.labs.service.AuthorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Author Service test")
@DataMongoTest
@Import({AuthorServiceImpl.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class AuthorServiceImplTest {

    @Autowired
    private AuthorService authorService;


    @DisplayName("Должен корректно сохранять информацию об авторе")
    @Test
    void shouldSaveAuthorInfo() {
        long beforeInsert = authorService.findAll().size();
        authorService.save("Author Service #Test");
        long afterInsert = authorService.findAll().size();
        System.out.println(authorService.findAll());
        assertThat(afterInsert).isGreaterThan(beforeInsert);
    }

    @DisplayName("Должен вернуть информацию о всех авторах")
    @Test
    void shouldReturnCorrectAuthorsListWithAllInfo() {
        assertTrue(authorService.findAll().isEmpty());
    }

    @DisplayName("Должен загружать информацию о нужном авторе")
    @Test
    void shouldFindExpectedAuthorById() throws AuthorNotExistException {
        authorService.save("Author Service #Test");
        String authorId = authorService.findAll().get(0).getId();
        assertThat(authorId).isNotNull();
        assertEquals("Author Service #Test", authorService.findById(authorId).getName());
    }

    @DisplayName("Должен выбрасывать исключение AuthorNotExistException, если автора не существует")
    @Test
    void shouldThrowAuthorNotExistExceptionWhenAuthorNotExist() {
        assertThrows(AuthorNotExistException.class,
                () -> authorService.findById("123"));
    }

    @DisplayName("Должен удалять автора")
    @Test
    void shouldRemoveAuthor() throws AuthorNotExistException {
        authorService.save("Author Service #Test");
        long beforeDelete = authorService.findAll().size();
        String authorId = authorService.findAll().get(0).getId();
        authorService.delete(authorId);
        long afterDelete = authorService.findAll().size();
        assertThat(afterDelete).isLessThan(beforeDelete);
    }

    @DisplayName("Должен вернуть количество авторов")
    @Test
    void count() {
        assertThat(authorService.count()).isNotNull();
    }
}