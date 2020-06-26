package com.mateo9x.webapp.repositories;

import com.mateo9x.webapp.model.Director;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DirectorRepository extends CrudRepository<Director, Long> {
    Optional<Director> getFirstByFirstNameAndLastName(String firstName, String lastName);
}
