package org.asm.labs.shell;

import org.asm.labs.entity.Genre;
import org.asm.labs.service.*;
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
                         @ShellOption String authorsNames,
                         @ShellOption String genreName) {
        try {
            bookService.add(title, authorsNames, genreName);
        } catch (BookAlreadyExistException e) {
            System.out.println(e.getMessage());
        }
    }

    @ShellMethod("get by title")
    public void get_book(@ShellOption String title) {
        System.out.println(bookService.getByTitle(title));
    }

    @ShellMethod("get by id")
    public void get_book_by_id(@ShellOption int id) {
        try {
            System.out.println(bookService.getById(id));
        } catch (BookDoesntExistException e) {
            System.out.println(e.getMessage());
        }
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
        try {
            bookService.getAllByAuthor(authorName).forEach(System.out::println);
        } catch (AuthorDoesntExistException e) {
            System.out.println(e.getMessage());
        }
        
    }

    @ShellMethod("remove book")
    public void remove_book(@ShellOption String title) {
        try {
            bookService.remove(title);
        } catch (BookDoesntExistException e) {
            System.out.println(e.getMessage());
        }

    }

    @ShellMethod("count")
    public void count_books() {
        System.out.println(bookService.count());
    }
}
