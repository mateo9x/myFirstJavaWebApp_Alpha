package com.mateo9x.webapp.converters;


import com.mateo9x.webapp.commands.FilmGenreCommand;
import com.mateo9x.webapp.model.FilmGenre;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class FilmGenreCommandToFilmGenre implements Converter<FilmGenreCommand, FilmGenre> {

@Synchronized
    @Nullable
    @Override
    public FilmGenre convert(FilmGenreCommand source){
    if (source == null){
        return null;
    }

    final FilmGenre filmGenre = new FilmGenre();
    filmGenre.setGenre(source.getGenre());
    return filmGenre;
}

}
