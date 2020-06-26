package com.mateo9x.webapp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(of = {"id"})
public class Distributor {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;


    public Distributor(String name){
        this.name = name;
    }

    public Distributor() {
    }

    @Override
    public String toString(){

        return "Distributor{" + "id=" + id + ", name=" +  name + "}";
    }



}
