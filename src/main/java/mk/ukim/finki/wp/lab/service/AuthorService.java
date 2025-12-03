package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    public List<Author> findAll();
    void like(Long id);
    Optional<Author> getAuthor(Long id);
}
