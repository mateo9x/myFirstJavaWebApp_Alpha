package com.mateo9x.webapp.controllers;

import com.mateo9x.webapp.commands.MovieCommand;
import com.mateo9x.webapp.converters.MovieCommandToMovie;
import com.mateo9x.webapp.model.Movie;
import com.mateo9x.webapp.repositories.DirectorRepository;
import com.mateo9x.webapp.repositories.DistributorRepository;
import com.mateo9x.webapp.repositories.FilmGenreRepository;
import com.mateo9x.webapp.repositories.MovieRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
public class MovieController {

    private MovieRepository movieRepository;
    private DirectorRepository directorRepository;
    private FilmGenreRepository filmGenreRepository;
    private DistributorRepository distributorRepository;
    private MovieCommandToMovie movieCommandToMovie;

    public MovieController(MovieRepository movieRepository, DirectorRepository directorRepository, DistributorRepository distributorRepository, FilmGenreRepository filmGenreRepository, MovieCommandToMovie movieCommandToMovie ){
        this.movieCommandToMovie = movieCommandToMovie;
        this.movieRepository = movieRepository;
        this.directorRepository = directorRepository;
        this.distributorRepository = distributorRepository;
        this.filmGenreRepository = filmGenreRepository;
    }

    @GetMapping
    @RequestMapping(value ={"/movies", "movie/list"})
    public String GetMovies(Model model){

        model.addAttribute("movies", movieRepository.findAll());
        return "movie/list";
    }

    @GetMapping("/movie/{id}/delete")
    public String deleteMovie(Model model, @PathVariable("id") Long id) {
        movieRepository.deleteById(id);
        model.addAttribute("movie", movieRepository.findAll());
        return "redirect:/";
    }

    @PostMapping("/movie/{id}")
    public String editMovie(Model model, @PathVariable("id") Long id, Movie movie, BindingResult result) {
        if (result.hasErrors()) {
            movie.setId(id);
            return "updated-movie";
        }
        movieRepository.save(movie);
        model.addAttribute("movie", movieRepository.findAll());
        return "redirect:/";
    }

    @GetMapping
    @RequestMapping("/movie/{id}/show")
    public String getMovieDetails(Model model, @PathVariable("id")Long id){
        model.addAttribute("movie", movieRepository.findById(id).get());
        return "movie/show";
    }

    @GetMapping("/movie/new")
    public String newMovie(Model model){
        model.addAttribute("movie", new MovieCommand());
        model.addAttribute("directors", directorRepository.findAll());
        return "movie/addeddit";
    }
    @PostMapping("movie")
    public String saveOrUpdate(@ModelAttribute MovieCommand command){

        Optional<Movie> movieOptional = movieRepository.getMovieByName(command.getName());

        if(!movieOptional.isPresent()){

            Movie detachedMovie = movieCommandToMovie.convert(command);
            Movie savedMovie = movieRepository.save(detachedMovie);
            return "redirect:/movie/" + savedMovie.getId() + "/show";
        }else{
            System.out.println("Sorry, there's such movie in db");
            return "redirect:/movie/" + movieOptional.get().getId() + "/show";
        }
    }

}
