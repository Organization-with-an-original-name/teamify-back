package org.original.name.teamify.repository;

import org.original.name.teamify.model.Team;
import org.original.name.teamify.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeamRepository extends CrudRepository<Team, Long> {
    boolean existsByName(String name);

    List<Team> findAll();

    List<Team> findAllByLeader(User leader);
}
