package com.mateo9x.webapp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(of = {"id"})
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @ManyToMany
    private Set<Director> directors = new HashSet<>();


    public Movie (String name){
        this.name = name;
    }

    public Movie(){}

    @Override
    public String toString(){
        return "Movie{" + "id=" + id + ", name=" + name + "}";
    }


}
