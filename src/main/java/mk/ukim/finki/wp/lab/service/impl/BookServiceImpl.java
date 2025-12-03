package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import mk.ukim.finki.wp.lab.repository.BookRepositoryJpa;
import mk.ukim.finki.wp.lab.service.AuthorService;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl  implements BookService {

    public final BookRepositoryJpa bookRepository;
    public final AuthorService authorService;

    public BookServiceImpl(BookRepositoryJpa bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    @Override
    public List<Book> listAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> searchBooks(String title, Double averageRating) {
        return bookRepository.findAllByTitleContainsIgnoreCaseOrAverageRating(title, averageRating);
    }

    @Override
    public Book getBook(Long id) {
        return this.bookRepository.findById(id).orElseThrow();
    }

    @Override
    public Book addBook(String title, String genre, Double averageRating, Long authorId) {
        Author author = this.authorService.getAuthor(authorId).orElseThrow();
        return this.bookRepository.save(new Book(title, genre, averageRating, author));
    }

    @Override
    public Book update(Long bookId, String title, String genre, Double averageRating, Long authorId) {
        if (title == null || title.isEmpty() || genre == null || genre.isEmpty() || averageRating == null || authorId == null) {
            throw new IllegalArgumentException();
        }

        Book book = this.bookRepository.findAll().stream().filter(book1 -> book1.getId().equals(bookId)).findFirst().orElseThrow();
        Author author = this.authorService.findAll().stream().filter(author1 -> author1.getId().equals(authorId)).findFirst().orElseThrow();

        book.setTitle(title);
        book.setGenre(genre);
        book.setAverageRating(averageRating);
        book.setAuthor(author);

        return this.bookRepository.save(book);
    }

    @Override
    public void delete(Long bookId) {
        this.bookRepository.deleteById(bookId);
    }
}
