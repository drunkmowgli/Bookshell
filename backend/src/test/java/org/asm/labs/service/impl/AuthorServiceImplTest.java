package org.asm.labs.service.impl;

import lombok.SneakyThrows;
import org.asm.labs.model.Author;
import org.asm.labs.repository.AuthorRepository;
import org.asm.labs.service.AuthorNotExistException;
import org.asm.labs.service.AuthorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Author Service test")
@DataJpaTest
@Import({AuthorServiceImpl.class})
class AuthorServiceImplTest {

	@Autowired
	private AuthorService authorService;

	@MockBean
	private AuthorRepository authorRepository;

	@Captor
	private ArgumentCaptor<Author> authorArgumentCaptor;


	@DisplayName("Должен корректно сохранять информацию об авторе")
	@Test
	void shouldSaveAuthorInfo() {
		authorService.save("Author Service #Test");
		verify(authorRepository).save(authorArgumentCaptor.capture());
		assertThat(authorArgumentCaptor.getAllValues()).isNotNull();
		assertEquals("Author Service #Test", authorArgumentCaptor.getValue().getName());
	}

	@DisplayName("Должен вернуть информацию о всех авторах")
	@Test
	void shouldReturnCorrectAuthorsListWithAllInfo() {
		List<Author> authors = authorService.findAll();
		assertTrue(authors.isEmpty());
	}

	@DisplayName("Должен загружать информацию о нужном авторе по ID")
	@Test
	@SneakyThrows
	void shouldFindExpectedAuthorById() {
		when(authorRepository.findById(1234567890L)).thenReturn(Optional.of(new Author("Author Service #Test")));
		String actualAuthorName = authorService.findById(1234567890L).getName();
		assertEquals("Author Service #Test", actualAuthorName);
	}

	@DisplayName("Должен загружать информацию о нужном авторе по имени")
	@Test
	@SneakyThrows
	void shouldFindExpectedAuthorByName() {
		when(authorRepository.findByName("Author Service #Test")).thenReturn(Optional.of(new Author("Author Service #Test")));
		String actualAuthorName = authorService.findByAuthorName("Author Service #Test").getName();
		assertEquals("Author Service #Test", actualAuthorName);
	}


	@DisplayName("Должен выбрасывать исключение AuthorNotExistException, если автора не существует")
	@Test
	void shouldThrowAuthorNotExistExceptionWhenAuthorNotExist() {
		assertThrows(AuthorNotExistException.class,
				() -> authorService.findById(10L));
	}

	@DisplayName("Должен удалять автора")
	@Test
	@SneakyThrows
	void shouldRemoveAuthor() {
		when(authorRepository.findById(1234567890L)).thenReturn(Optional.of(new Author("Author Service #Test")));
		authorService.delete(1234567890);
		verify(authorRepository).delete(authorArgumentCaptor.capture());
		assertThat(authorArgumentCaptor.getAllValues()).isNotNull();
		assertEquals("Author Service #Test", authorArgumentCaptor.getValue().getName());
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