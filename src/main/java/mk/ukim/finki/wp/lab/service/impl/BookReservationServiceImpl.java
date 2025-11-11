package mk.ukim.finki.wp.lab.service.impl;


import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.repository.BookReservationRepository;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookReservationServiceImpl implements BookReservationService {
    private final BookReservationRepository bookReservationRepository;
    private final BookService bookService;

    public BookReservationServiceImpl(BookReservationRepository bookReservationRepository, BookService bookService) {
        this.bookReservationRepository = bookReservationRepository;
        this.bookService = bookService;
    }

    @Override
    public BookReservation placeReservation(Long bookId, String bookTitle, String readerName, String readerAddress, int numberOfCopies) {
        Book book = this.bookService.getBook(bookId);
        return this.bookReservationRepository.save(new BookReservation(book, readerName, readerAddress, (long) numberOfCopies));
    }

    @Override
    public List<BookReservation> list() {
        return this.bookReservationRepository.list();
    }

}
