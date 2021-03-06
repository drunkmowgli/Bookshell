package org.asm.labs.service.impl;

import org.asm.labs.model.Author;
import org.asm.labs.model.Book;
import org.asm.labs.model.Genre;
import org.asm.labs.repository.BookRepository;
import org.asm.labs.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class BookServiceImpl implements BookService {


	private final BookRepository bookRepository;

	private final AuthorService authorService;

	private final GenreService genreService;


	@Autowired
	public BookServiceImpl(BookRepository bookRepository,
	                       AuthorService authorService,
	                       GenreService genreService) {
		this.bookRepository = bookRepository;
		this.authorService = authorService;
		this.genreService = genreService;
	}


	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
	public Book save(String title, List<Long> authorIds, String genreName) throws GenreNotExistException, AuthorNotExistException {
		Set<Author> authors = new HashSet<>();
		for (long authorId :
				authorIds) {
			Author author = authorService.findById(authorId);
			authors.add(author);
		}
		Genre genre = genreService.findByGenreName(genreName);
		Book book = new Book(title, authors, genre);
		return bookRepository.save(book);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
	public Book update(long bookId, String title, List<Long> authorsIds, String genreName) throws BookNotExistException, AuthorNotExistException, GenreNotExistException {
		Book book = bookRepository.findById(bookId).orElseThrow(BookNotExistException::new);
		book.setTitle(title);
		Set<Author> authors = new HashSet<>();
		for (long authorId : authorsIds) {
			Author author = authorService.findById(authorId);
			authors.add(author);
		}
		book.setAuthors(authors);
		Genre genre = genreService.findByGenreName(genreName);
		book.setGenre(genre);
		return bookRepository.save(book);
	}

	@Override
	public List<Book> findAll() {
		return bookRepository.findAll();
	}

	@Override
	public Book findById(long id) throws BookNotExistException {
		return bookRepository.findById(id).orElseThrow(BookNotExistException::new);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
	public void remove(long bookId) throws BookNotExistException {
		Book book = bookRepository.findById(bookId).orElseThrow(BookNotExistException::new);
		bookRepository.delete(book);
	}

	@Override
	public long count() {
		return bookRepository.count();
	}
}