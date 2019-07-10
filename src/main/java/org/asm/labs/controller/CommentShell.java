package org.asm.labs.controller;

import org.asm.labs.model.Book;
import org.asm.labs.model.Comment;
import org.asm.labs.service.BookNotExistException;
import org.asm.labs.service.BookService;
import org.asm.labs.service.CommentNotExistException;
import org.asm.labs.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class CommentShell {

    private final CommentService commentService;

    private final BookService bookService;

    @Autowired
    public CommentShell(CommentService commentService, BookService bookService) {
        this.commentService = commentService;
        this.bookService = bookService;
    }

    @ShellMethod("save comment")
    public void add_comment(@ShellOption String commentDescription,
                            @ShellOption long bookId) {
        try {
            Book book = bookService.findById(bookId);
            commentService.save(new Comment(commentDescription, book));
        } catch (BookNotExistException e) {
            System.out.println(e.getMessage());
        }

    }

    @ShellMethod("get all")
    public void get_all_comments() {
        commentService.findAll().forEach(System.out::println);
    }

    @ShellMethod("get by id")
    public void get_comment(@ShellOption long id) {
        try {
            System.out.println(commentService.findById(id));
        } catch (CommentNotExistException e) {
            System.out.println(e.getMessage());
        }
    }

    @ShellMethod("delete comment")
    public void remove_comment(@ShellOption long commentId) {
        try {
            commentService.remove(commentId);
        } catch (CommentNotExistException e) {
            System.out.println(e.getMessage());
        }
    }

    @ShellMethod("count")
    public void count_comments() {
        System.out.println(commentService.count());
    }
}
