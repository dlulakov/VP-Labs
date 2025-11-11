package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> listAll();

    List<Book> searchBooks(String text, Double rating);

    Book getBook(Long id);

    Book addBook(String title, String genre, Double averageRating, Long authorId);

    Book update(Long bookId, String title, String genre, Double averageRating, Long authorId);

    void delete(Long bookId);
}
