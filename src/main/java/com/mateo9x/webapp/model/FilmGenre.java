package com.mateo9x.webapp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@EqualsAndHashCode(of = {"id"})
public class FilmGenre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String genre;


    public FilmGenre(String genre){
        this.genre = genre;
    }

    public FilmGenre(){}

    @Override
    public String toString(){

        return "FilmGenre{" + "id=" + id + ", genre=" + genre + "}";
    }




}
