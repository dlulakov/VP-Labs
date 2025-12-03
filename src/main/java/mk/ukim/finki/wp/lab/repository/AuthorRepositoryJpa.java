package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepositoryJpa extends JpaRepository<Author, Long> {

    @Query("UPDATE Author a SET a.likes = a.likes + 1 WHERE a.id = :id")
    void incrementLikes(@Param("id") Long id);
}
