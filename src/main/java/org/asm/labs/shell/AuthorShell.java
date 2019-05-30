package org.asm.labs.shell;


import org.asm.labs.service.AuthorAlreadyExistException;
import org.asm.labs.service.AuthorDoesntExistException;
import org.asm.labs.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class AuthorShell {

    private final AuthorService authorService;


    @Autowired
    public AuthorShell(AuthorService authorService) {
        this.authorService = authorService;
    }

    @ShellMethod("add author")
    public void add_author(@ShellOption String authorName) {
        try {
            authorService.add(authorName);
        } catch (AuthorAlreadyExistException e) {
            System.out.println(e.getMessage());
        }
    }

    @ShellMethod("get all")
    public void get_all_authors() {
        authorService.getAll().forEach(System.out::println);
    }

    @ShellMethod("get by id")
    public void get_author_by_id(@ShellOption int id) {
        try {
            System.out.println(authorService.getById(id));
        } catch (AuthorDoesntExistException e) {
            System.out.println(e.getMessage());
        }
    }

    @ShellMethod("remove author")
    public void remove_author(@ShellOption int authorId) {
        try {
            authorService.remove(authorId);
        } catch (AuthorDoesntExistException e) {
            System.out.println(e.getMessage());
        }

    }

    @ShellMethod("count")
    public void count_authors() {
        System.out.println(authorService.count());
    }
}
