package com.mateo9x.webapp.repositories;

import com.mateo9x.webapp.model.FilmGenre;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface FilmGenreRepository extends CrudRepository<FilmGenre, Long> {
    Optional<FilmGenre> getFilmGenreByGenre(String genre);
}
