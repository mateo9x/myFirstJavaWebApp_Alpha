package com.mateo9x.webapp.repositories;

import com.mateo9x.webapp.model.Distributor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DistributorRepository extends CrudRepository<Distributor, Long> {
    Optional<Distributor> getDistributorByName(String name);
}
