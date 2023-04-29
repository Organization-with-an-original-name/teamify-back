package org.original.name.teamify.repository;

import org.original.name.teamify.model.Team;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team,Long> {
    boolean existsByName(String name);

}
