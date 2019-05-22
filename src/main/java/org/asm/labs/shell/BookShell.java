package org.asm.labs.shell;

import org.asm.labs.entity.Book;
import org.asm.labs.service.AuthorService;
import org.asm.labs.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class BookShell {

    private final BookService bookService;

    private final AuthorService authorService;
    

    @Autowired
    public BookShell(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

//    @ShellMethod("add book")
//    public void add_book(@ShellOption String title,
//                         @ShellOption String authorName,
//                         @ShellOption String genreName) {
//        Author author = authorService.getByName(authorName);
//        bookService.add(new Book(title, author));
//    }

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

    @ShellMethod("remove book")
    public void remove_book(@ShellOption Book book) {
        bookService.remove(book);
    }

    @ShellMethod("count")
    public void count_books() {
        System.out.println(bookService.count());
    }
}
