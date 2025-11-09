package mk.ukim.finki.wp.lab.repository.impl;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.repository.AuthorRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository {

    private final  List<Author> authors;

    public AuthorRepositoryImpl() {
        this.authors = new ArrayList<>() ;
        authors.add(new Author("Isabella", "Moreau", "France", "Isabella Moreau (born 1983, Lyon, France) is a French novelist and essayist best known for her lyrical prose and explorations of memory and identity. Her debut novel Les Ombres du Rhône received the Prix Médicis in 2014 and established her as one of the leading voices in contemporary French literature."));
        authors.add(new Author("Marcus", "Hale", "United States", "Marcus Hale (born 1976, Seattle, USA) is an American science fiction and fantasy author. After earning a degree in astrophysics,"));
        authors.add(new Author("Amara", "Moreau", "South Africa", "Amara Ndlovu (born 1990, Johannesburg, South Africa) is a South African poet and novelist whose works address themes of gender, resilience, and post-apartheid identity."));
    }

    @Override
    public List<Author> findAll() {
        return this.authors;
    }
}
