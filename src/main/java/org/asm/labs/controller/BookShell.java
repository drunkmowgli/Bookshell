package org.asm.labs.controller;

import org.asm.labs.service.AuthorNotExistException;
import org.asm.labs.service.BookNotExistException;
import org.asm.labs.service.BookService;
import org.asm.labs.service.GenreNotExistException;
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

    @ShellMethod("save book")
    public void add_book(@ShellOption String title,
                         @ShellOption String authorsNames,
                         @ShellOption long genreId) {
        try {
            bookService.save(title, authorsNames, genreId);
        } catch (AuthorNotExistException | GenreNotExistException e) {
            System.out.println(e.getMessage());
        }
    }

    @ShellMethod("get by id")
    public void get_book(@ShellOption long id) {
        try {
            System.out.println(bookService.findById(id));
        } catch (BookNotExistException e) {
            System.out.println(e.getMessage());
        }
    }

    @ShellMethod("get all")
    public void get_all_books() {
        bookService.findAll().forEach(System.out::println);
    }

    @ShellMethod("delete book")
    public void remove_book(@ShellOption long id) {
        try {
            bookService.remove(id);
        } catch (BookNotExistException e) {
            System.out.println(e.getMessage());
        }

    }

    @ShellMethod("count")
    public void count_books() {
        System.out.println(bookService.count());
    }
}
