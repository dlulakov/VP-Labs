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

        books.forEach(System.out::println);

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

    @PostMapping("/edit/{bookId}")
    public String editBook(@PathVariable Long bookId, @RequestParam String title, @RequestParam String genre, @RequestParam Double averageRating, @RequestParam Long authorId){
        this.bookService.update(bookId,title, genre, averageRating, authorId);
        if(bookId == null){
            return "redirect/books?error=BookNotFound";
        }
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id){
        this.bookService.delete(id);
        return "redirect:/books";
    }

    @GetMapping("/book-form/{id}")
    public String getEditBookForm(@PathVariable Long id, Model model) {
        Book book = this.bookService.getBook(id);
        List<Author> authors = this.authorService.findAll();
        model.addAttribute("book", book);
        model.addAttribute("authors", authors);
        return "book-form";
    }

    @GetMapping("/book-form")
    public String addBook(Model model) {
        return "book-form";
    }

}
