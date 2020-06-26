package com.mateo9x.webapp.controllers;

import com.mateo9x.webapp.commands.FilmGenreCommand;
import com.mateo9x.webapp.converters.FilmGenreCommandToFilmGenre;
import com.mateo9x.webapp.model.Distributor;
import com.mateo9x.webapp.model.FilmGenre;
import com.mateo9x.webapp.repositories.DirectorRepository;
import com.mateo9x.webapp.repositories.DistributorRepository;
import com.mateo9x.webapp.repositories.FilmGenreRepository;
import com.mateo9x.webapp.repositories.MovieRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class FilmGenreController {

    private FilmGenreRepository filmGenreRepository;
    private FilmGenreCommandToFilmGenre filmGenreCommandToFilmGenre;
    private DirectorRepository directorRepository;
    private DistributorRepository distributorRepository;
    private MovieRepository movieRepository;

    public FilmGenreController(FilmGenreRepository filmGenreRepository, FilmGenreCommandToFilmGenre filmGenreCommandToFilmGenre, MovieRepository movieRepository, DistributorRepository distributorRepository, DirectorRepository directorRepository){
        this.filmGenreCommandToFilmGenre = filmGenreCommandToFilmGenre;
        this.filmGenreRepository = filmGenreRepository;
        this.movieRepository=movieRepository;
        this.directorRepository=directorRepository;
        this.distributorRepository=distributorRepository;
    }

    @GetMapping
    @RequestMapping(value={"/filmGenres", "filmGenre/list"})
    public String getFilmGenres(Model model){
        model.addAttribute("filmGenres", filmGenreRepository.findAll());
        return "filmGenre/list";
    }

    @GetMapping
    @RequestMapping("/filmGenre/{id}/show")
    public String getFilmGenreDetails(Model model, @PathVariable("id")Long id){
        model.addAttribute("filmGenre", filmGenreRepository.findById(id).get());
        return "filmGenre/show";
    }

    @GetMapping
    @RequestMapping("/filmGenre/{id}/delete")
    public String deleteFilmGenre(@PathVariable("id")Long id){
        filmGenreRepository.deleteById(id);
        return "redirect:/filmGenres";
    }

    @GetMapping
    @RequestMapping("/filmGenre/new")
    public String newFilmGenre(Model model){
        model.addAttribute("filmGenre", new FilmGenreCommand());
        model.addAttribute("movies", movieRepository.findAll());
        return "filmGenre/addeddit";
    }

    @PostMapping("filmGenre")
    public String saveOrUpdate(@ModelAttribute FilmGenreCommand command){

        Optional<FilmGenre> filmGenreOptional = filmGenreRepository.getFilmGenreByGenre(command.getGenre());
        if (!filmGenreOptional.isPresent()) {
        FilmGenre detachedFilmGenre = filmGenreCommandToFilmGenre.convert(command);
        FilmGenre savedFilmGenre = filmGenreRepository.save(detachedFilmGenre);
        return "redirect:/filmGenre/" + savedFilmGenre.getId() + "/show";
        } else {
        //TODO : error message
        System.out.println("Sorry, there's such distributor in DB");
        return "redirect:/distributor/" + filmGenreOptional.get().getId() + "/show";
    }
    }

}
