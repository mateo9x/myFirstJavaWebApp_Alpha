package com.mateo9x.webapp.repositories;

import com.mateo9x.webapp.model.Director;
import com.mateo9x.webapp.model.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieRepository extends CrudRepository<Movie, Long> {
    List<Movie> getAllByDirectorsIsContaining(Director director);
}
