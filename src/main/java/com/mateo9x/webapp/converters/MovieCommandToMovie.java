package com.mateo9x.webapp.converters;


import com.mateo9x.webapp.commands.MovieCommand;
import com.mateo9x.webapp.model.Movie;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class MovieCommandToMovie implements Converter<MovieCommand, Movie > {

@Synchronized
    @Nullable
    @Override
    public Movie convert (MovieCommand source){
    if (source == null) {
        return null;
    }
    final Movie movie = new Movie();
    movie.setName(source.getName());
    return movie;
}
}
