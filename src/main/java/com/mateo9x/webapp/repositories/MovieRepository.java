package com.mateo9x.webapp.repositories;

import com.mateo9x.webapp.model.Director;
import com.mateo9x.webapp.model.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends CrudRepository<Movie, Long> {
    Optional<Movie> getMovieByName(String name);
}
