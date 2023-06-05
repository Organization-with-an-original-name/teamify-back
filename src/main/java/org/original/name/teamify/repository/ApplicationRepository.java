package org.original.name.teamify.repository;

import org.original.name.teamify.model.Application;
import org.original.name.teamify.model.Team;
import org.original.name.teamify.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends CrudRepository<Application, Long> {
    List<Application> findAllByTeamIn(List<Team> teams);

    List<Application> findAllByApplicant(User user);
}
