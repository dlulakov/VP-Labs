package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Book;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface BookRepository {
    List<Book> findAll();
    List<Book> searchBooks(String text, Double rating);
    Optional<Book> getBook(String title);
    Book addBook (Book book);
    void delete (Long id);
}
