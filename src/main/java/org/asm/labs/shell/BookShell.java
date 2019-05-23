package org.asm.labs.shell;

import org.asm.labs.entity.Author;
import org.asm.labs.entity.Book;
import org.asm.labs.entity.Genre;
import org.asm.labs.service.AuthorService;
import org.asm.labs.service.BookService;
import org.asm.labs.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class BookShell {

    private final BookService bookService;

    private final AuthorService authorService;

    private final GenreService genreService;
    

    @Autowired
    public BookShell(BookService bookService, AuthorService authorService, GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @ShellMethod("add book")
    public void add_book(@ShellOption String title,
                         @ShellOption String authorName,
                         @ShellOption String genreName) {
        authorService.add(new Author(authorName));
        genreService.add(new Genre(genreName));
        bookService.add(new Book(title, authorService.getByName(authorName), genreService.getByGenreName(genreName)));
    }

    @ShellMethod("get by title")
    public void get_book(@ShellOption String title) {
        System.out.println(bookService.getByTitle(title));
    }

    @ShellMethod("get by id")
    public void get_book_by_id(@ShellOption int id) {
        System.out.println(bookService.getById(id));
    }

    @ShellMethod("get all")
    public void get_all_books() {
        bookService.getAll().forEach(System.out::println);
    }

    @ShellMethod("get all by genre")
    public void get_all_books_by_genre(@ShellOption String genreName) {
        Genre genre = genreService.getByGenreName(genreName);
        bookService.getAllByGenre(genre).forEach(System.out::println);
    }

    @ShellMethod("get all by author")
    public void get_all_books_by_author(@ShellOption String authorName) {
        Author author = authorService.getByName(authorName);
        bookService.getAllByAuthor(author).forEach(System.out::println);
    }

    @ShellMethod("remove book")
    public void remove_book(@ShellOption String title) {
        Book book = bookService.getByTitle(title);
        bookService.remove(book);
    }

    @ShellMethod("count")
    public void count_books() {
        System.out.println(bookService.count());
    }
}
