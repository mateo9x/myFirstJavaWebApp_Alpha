package com.mateo9x.webapp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(of = {"id"})
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String pseudo;

    @ManyToMany(mappedBy = "directors")
    private Set<Movie> movies = new HashSet<>();

    public Director(String firstName, String lastName, String pseudo){

        this.firstName = firstName;
        this.lastName = lastName;
        this.pseudo = pseudo;
    }
    public Director(){}

    @Override
    public String toString() {

        return "Director{" + "id=" + id + ", firstName=" + firstName +
                ", lastName=" + lastName + ", pseudo=" + pseudo + "}";

    }

}
