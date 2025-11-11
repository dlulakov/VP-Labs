package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryBookRepository implements BookRepository {
    @Override
    public List<Book> findAll() {
        return DataHolder.books;
    }

    @Override
    public List<Book> searchBooks(String text, Double rating) {
        return DataHolder.books.stream().filter(book -> book.getTitle().contains(text) && rating <= book.getAverageRating()).toList();
    }

    @Override
    public Optional<Book> getBook(Long id) {
        return DataHolder.books.stream().filter(book -> book.getId().equals(id)).findFirst();
    }

    @Override
    public Book addBook(Book book) {
        delete(book.getId());
        DataHolder.books.add(book);
        return book;
    }

    @Override
    public void delete(Long id) {
        DataHolder.books.removeIf(book -> book.getId().equals(id));
    }

}
