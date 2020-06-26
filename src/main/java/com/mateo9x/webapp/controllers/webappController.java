package com.mateo9x.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@Controller
public class webappController {

    @RequestMapping(value = {"/"})
    public String getMovies(){
        return "index";
    }
}
