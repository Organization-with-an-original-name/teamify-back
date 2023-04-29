package org.original.name.teamify.service;

import lombok.RequiredArgsConstructor;
import org.original.name.teamify.dto.CreateTeamRequest;
import org.original.name.teamify.exception.TeamifyException;
import org.original.name.teamify.model.Team;
import org.original.name.teamify.model.User;
import org.original.name.teamify.repository.TeamRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;
    private final UserService userService;

    public Team getById(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new TeamifyException("Team with id " + id + " does not exists"));
    }

    public Team createTeam(CreateTeamRequest createTeamRequest) {
        if (teamRepository.existsByName(createTeamRequest.getName())) {
            throw new TeamifyException("Team with name " + createTeamRequest + " already exists");
        }
        User leader = userService.getUser(createTeamRequest.getLeaderId());
        Team team = new Team();

        BeanUtils.copyProperties(createTeamRequest, team);
        team.setLeader(leader);

        teamRepository.save(team);
        return team;
    }

}
