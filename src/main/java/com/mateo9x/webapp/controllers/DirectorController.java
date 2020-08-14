package com.mateo9x.webapp.controllers;

import com.mateo9x.webapp.commands.DirectorCommand;
import com.mateo9x.webapp.converters.DirectorCommandtoDirector;
import com.mateo9x.webapp.model.Director;
import com.mateo9x.webapp.repositories.DirectorRepository;
import com.mateo9x.webapp.repositories.DistributorRepository;
import com.mateo9x.webapp.repositories.FilmGenreRepository;
import com.mateo9x.webapp.repositories.MovieRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class DirectorController {

    private DirectorRepository directorRepository;
    private DistributorRepository distributorRepository;
    private FilmGenreRepository filmGenreRepository;
    private MovieRepository movieRepository;
    private DirectorCommandtoDirector directorCommandtoDirector;

    public DirectorController(DirectorCommandtoDirector directorCommandtoDirector ,DirectorRepository directorRepository, DistributorRepository distributorRepository, MovieRepository movieRepository, FilmGenreRepository filmGenreRepository ){

        this.directorRepository = directorRepository;
        this.distributorRepository =distributorRepository;
        this.movieRepository = movieRepository;
        this.filmGenreRepository= filmGenreRepository;
        this.directorCommandtoDirector = directorCommandtoDirector;
    }

    @RequestMapping(value = {"/directors", "/director/list"})
    public String getDirectors(Model model){
        model.addAttribute("directors", directorRepository.findAll());
        return "director/list";
    }


    @RequestMapping("/director/{id}/show")
    public String getDirectorDetails(Model model, @PathVariable("id")Long id){
        model.addAttribute("director", directorRepository.findById(id).get());
        return "director/show";
    }

    @GetMapping("/director/{id}/delete")
    public String deleteDirector(Model model, @PathVariable("id")Long id){
        directorRepository.deleteById(id);
        model.addAttribute("director", directorRepository.findAll());
        return "redirect:/";
    }

    @GetMapping("/director/{id}/addeddit")
    public String editDirector(@PathVariable("id") long id, Model model) {
        Director director = directorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("director", director);
        return "director/addeddit";
    }

    @GetMapping
    @RequestMapping("/director/new")
    public String newMovie(Model model){
        model.addAttribute("director", new DirectorCommand());
        return "director/addeddit";
    }

    @PostMapping("director")
    public String saveOrUpdate(@ModelAttribute DirectorCommand command){

        Optional<Director> directorOptional = directorRepository.getFirstByFirstNameAndLastName(command.getFirstName(),command.getLastName());
        if (!directorOptional.isPresent()){
            Director detachedDirector = directorCommandtoDirector.convert(command);
            Director savedDirector = directorRepository.save(detachedDirector);
            return "redirect:/director/" + savedDirector.getId() + "/show";
        } else {
            //TODO : error message
            System.out.println ("Sorry, there's such director in DB");
            return "redirect:/director/" + directorOptional.get().getId() + "/show";
        }
    }

}
