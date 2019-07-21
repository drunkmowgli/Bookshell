package org.asm.labs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookViewController {


    @GetMapping("/")
    public String getBooksPage() {
        return "books";
    }


}
