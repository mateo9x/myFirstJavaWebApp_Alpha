package com.mateo9x.webapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FilmGenreCommand {
    private Long id;
    private String genre;
}
