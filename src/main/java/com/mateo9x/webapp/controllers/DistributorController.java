package com.mateo9x.webapp.controllers;

import com.mateo9x.webapp.commands.DistributorCommand;
import com.mateo9x.webapp.converters.DistributorCommandToDistributor;
import com.mateo9x.webapp.model.Director;
import com.mateo9x.webapp.model.Distributor;
import com.mateo9x.webapp.model.Movie;
import com.mateo9x.webapp.repositories.DirectorRepository;
import com.mateo9x.webapp.repositories.DistributorRepository;
import com.mateo9x.webapp.repositories.FilmGenreRepository;
import com.mateo9x.webapp.repositories.MovieRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class DistributorController {

    private DistributorRepository distributorRepository;
    private DistributorCommandToDistributor distributorCommandToDistributor;
    private MovieRepository movieRepository;
    private FilmGenreRepository filmGenreRepository;
    private DirectorRepository directorRepository;

    public DistributorController(DistributorRepository distributorRepository, DistributorCommandToDistributor distributorCommandToDistributor, MovieRepository movieRepository, FilmGenreRepository filmGenreRepository, DirectorRepository directorRepository){
        this.directorRepository= directorRepository;
        this.movieRepository= movieRepository;
        this.distributorCommandToDistributor = distributorCommandToDistributor;
        this.filmGenreRepository= filmGenreRepository;
        this.distributorRepository=distributorRepository;

    }

    @GetMapping
    @RequestMapping(value={"/distributors", "distributor/list"})
    public String getDistributors(Model model){
        model.addAttribute("distributors", distributorRepository.findAll());
        return "distributor/list";
    }

    @GetMapping
    @RequestMapping("/distributor/{id}/show")
    public String getDistributorDetails(Model model, @PathVariable("id")Long id){
        model.addAttribute("distributor", distributorRepository.findById(id).get());
        return "distributor/show";
    }
    @GetMapping("/distributor/{id}/delete")
    public String deleteDistributor(@PathVariable("id")Long id,Model model ){
        distributorRepository.deleteById(id);
        model.addAttribute("distributor", distributorRepository.findAll());
        return "redirect:/";
    }


    @GetMapping("/distributor/{id}/addeddit")
    public String editDistributor(@PathVariable("id") long id, Model model) {
        Distributor distributor = distributorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("distributor", distributor);
        return "distributor/addeddit";
    }

    @GetMapping
    @RequestMapping("/distributor/new")
    public String newDistributor(Model model){
        model.addAttribute("distributor", new DistributorCommand());
        model.addAttribute("movie", movieRepository.findAll());
        return "distributor/addeddit";
    }

    @PostMapping("distributor")
    public String saveOrUpdate(@ModelAttribute DistributorCommand command){

        Optional<Distributor> distributorOptional = distributorRepository.getDistributorByName(command.getName());
        if (!distributorOptional.isPresent()) {
            Distributor detachedDistributor = distributorCommandToDistributor.convert(command);
            Distributor savedDistributor = distributorRepository.save(detachedDistributor);
            return "redirect:/distributor/" + savedDistributor.getId() + "/show";
        } else{
            //TODO : error message
            System.out.println ("Sorry, there's such distributor in DB");
            return "redirect:/distributor/" + distributorOptional.get().getId() + "/show";
        }
    }

}
