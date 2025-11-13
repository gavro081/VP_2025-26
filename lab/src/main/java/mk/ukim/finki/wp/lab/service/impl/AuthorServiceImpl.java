package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.repository.impl.AuthorRepositoryImpl;
import mk.ukim.finki.wp.lab.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepositoryImpl authorRepository;

    public AuthorServiceImpl(AuthorRepositoryImpl authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author findByID(Long id) {
        return authorRepository.getById(id);
    }

    @Override
    public void incrementLikesByAuthor(Long id) {
        authorRepository.incrementLikesById(id);
    }
}
