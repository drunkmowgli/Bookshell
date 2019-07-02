package org.asm.labs.service.impl;

import org.asm.labs.entity.Author;
import org.asm.labs.repository.impl.AuthorRepositoryJpaImpl;
import org.asm.labs.service.AuthorNotExistException;
import org.asm.labs.service.AuthorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Author Service test")
@DataJpaTest(properties = "spring.profiles.active=test")
@Import({AuthorServiceImpl.class, AuthorRepositoryJpaImpl.class})
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class AuthorServiceImplTest {

    @Autowired
    private AuthorService authorService;


    @DisplayName("Должен корректно сохранять информацию об авторе")
    @Test
    void shouldSaveAuthorInfo() {
        authorService.save(new Author("Author Service #Test"));
        assertEquals(4, authorService.findAll().size());
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

    @DisplayName("Должен выбрасывать исключение NoResultException, если автора не существует")
    @Test
    void shouldThrowNoResultExceptionWhenAuthorNotExist() {
        assertThrows(AuthorNotExistException.class,
                () -> authorService.findById(10));
    }

    @DisplayName("Должен удалять автора")
    @Test
    void shouldRemoveAuthor() throws AuthorNotExistException {
        authorService.remove(1);
        assertEquals(2, authorService.findAll().size());
    }

    @DisplayName("Должен выбрасывать исключение AuthorNotExistException, если автора не существует")
    @Test
    void shouldThrowCommentNotExistExceptionWhenAuthorNotExistOnRemove() {
        assertThrows(AuthorNotExistException.class,
                () -> {authorService.remove(10);});
    }

    @DisplayName("Должен вернуть количество авторов")
    @Test
    void count() {
        assertEquals(3, authorService.count());
    }
}