package com.mateo9x.webapp.tools;

import com.mateo9x.webapp.model.Director;
import com.mateo9x.webapp.model.Distributor;
import com.mateo9x.webapp.model.FilmGenre;
import com.mateo9x.webapp.model.Movie;
import com.mateo9x.webapp.repositories.DirectorRepository;
import com.mateo9x.webapp.repositories.DistributorRepository;
import com.mateo9x.webapp.repositories.FilmGenreRepository;
import com.mateo9x.webapp.repositories.MovieRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DBInflater implements ApplicationListener<ContextRefreshedEvent> {

    public DBInflater(DirectorRepository directorRepository, DistributorRepository distributorRepository, FilmGenreRepository filmGenreRepository, MovieRepository movieRepository) {

        this.directorRepository = directorRepository;
        this.filmGenreRepository = filmGenreRepository;
        this.movieRepository = movieRepository;
        this.distributorRepository = distributorRepository;
    }

    private DirectorRepository directorRepository;
    private DistributorRepository distributorRepository;
    private MovieRepository movieRepository;
    private FilmGenreRepository filmGenreRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent)
    { iniData(); }

    private void iniData(){

        Director georgelucas = new Director("George", "Lucas", "GL" );
        Movie starwars = new Movie("Star Wars");
        georgelucas.getMovies().add(starwars);
        starwars.getDirectors().add(georgelucas);
        directorRepository.save(georgelucas);
        movieRepository.save(starwars);

        Director alpacino = new Director("Al", "Pacino", "Alpa" );
        Movie godfather = new Movie("God Father");
        alpacino.getMovies().add(godfather);
        godfather.getDirectors().add(alpacino);
        directorRepository.save(alpacino);
        movieRepository.save(godfather);

        Director tarantino = new Director("Quentin", "Tarantino", "Taran" );
        Movie django = new Movie("Django");
        tarantino.getMovies().add(django);
        django.getDirectors().add(tarantino);
        directorRepository.save(tarantino);
        movieRepository.save(django);

        Director chrisnolan = new Director("Christopher", "Nolan", "ChrisNolan" );
        Movie dark_knight_returns = new Movie("Dark Knight Returns");
        chrisnolan.getMovies().add(dark_knight_returns);
        dark_knight_returns.getDirectors().add(chrisnolan);
        directorRepository.save(chrisnolan);
        movieRepository.save(dark_knight_returns);




    }

}