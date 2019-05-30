package org.asm.labs.shell;

import org.asm.labs.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class BookShell {

    private final BookService bookService;


    @Autowired
    public BookShell(BookService bookService) {
        this.bookService = bookService;
    }

    @ShellMethod("add book")
    public void add_book(@ShellOption String title,
                         @ShellOption String authorsNames,
                         @ShellOption int genreId) {
        try {
            bookService.add(title, authorsNames, genreId);
        } catch (BookAlreadyExistException | AuthorDoesntExistException | GenreDoesntExistException e) {
            System.out.println(e.getMessage());
        }
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

    @ShellMethod("remove book")
    public void remove_book(@ShellOption int id) {
        try {
            bookService.remove(id);
        } catch (BookDoesntExistException e) {
            System.out.println(e.getMessage());
        }

    }

    @ShellMethod("count")
    public void count_books() {
        System.out.println(bookService.count());
    }
}
