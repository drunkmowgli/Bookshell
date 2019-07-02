package org.asm.labs.shell;


import org.asm.labs.entity.Author;
import org.asm.labs.service.AuthorNotExistException;
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

    @ShellMethod("save author")
    public void add_author(@ShellOption String authorName) {
        authorService.save(new Author(authorName));
    }

    @ShellMethod("get all")
    public void get_all_authors() {
        authorService.findAll().forEach(System.out::println);
    }

    @ShellMethod("get by id")
    public void get_author(@ShellOption int id) {
        try {
            System.out.println(authorService.findById(id));
        } catch (AuthorNotExistException e) {
            System.out.println(e.getMessage());
        }
    }

    @ShellMethod("remove author")
    public void remove_author(@ShellOption int authorId) {
        try {
            authorService.remove(authorId);
        } catch (AuthorNotExistException e) {
            System.out.println(e.getMessage());
        }

    }

    @ShellMethod("count")
    public void count_authors() {
        System.out.println(authorService.count());
    }
}
