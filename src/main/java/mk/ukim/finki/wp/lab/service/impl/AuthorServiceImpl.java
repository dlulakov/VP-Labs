package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.repository.AuthorRepositoryJpa;
import mk.ukim.finki.wp.lab.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private  final AuthorRepositoryJpa authorRepository;

    public AuthorServiceImpl(AuthorRepositoryJpa authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public void like(Long id) {
        this.authorRepository.incrementLikes(id);
    }

    @Override
    public Optional<Author> getAuthor(Long id) {
        return this.authorRepository.findById(id);
    }
}
