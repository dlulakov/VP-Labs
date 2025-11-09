package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.service.AuthorService;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping
    public String getBooksPage(@RequestParam(required = false) String error, Model model){
        if(error != null){
            model.addAttribute("error", error);
        }
        List<Book> books = this.bookService.listAll();

        model.addAttribute("books", books);
        return "listBooks";
    }

    @GetMapping("/add")
    public String saveBook(Model model) {
        List<Author> authors = this.authorService.findAll();
        model.addAttribute("authors", authors);
        return "book-form";
    }

    @PostMapping("/add")
    public String saveBook(@RequestParam String title, @RequestParam String genre, @RequestParam Double averageRating, @RequestParam Long authorId) {
        this.bookService.addBook(title, genre, averageRating, authorId);
        return "redirect:/books";
    }

    @PatchMapping("/edit/{bookId}")
    public String editBook(@PathVariable Long bookId, @RequestParam String title, @RequestParam String genre, @RequestParam Double averageRating, @RequestParam Long authorId){
        this.bookService.update(bookId,title, genre, averageRating, authorId);
        return "redirect:/books";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id){
        this.bookService.delete(id);
        return "redirect:/books";
    }

}
