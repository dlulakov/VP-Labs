package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepositoryJpa extends JpaRepository<Book, Long> {
    List<Book> findAllByAuthor_Id(Long authorId);
    List<Book> findAllByTitleContainsIgnoreCaseOrAverageRating(String title, Double averageRating);
    @Query("SELECT b FROM Book b JOIN b.author a ORDER BY a.surname")
    List<Book> findAllBooksOrderedByAuthorSurname();}
