package org.original.name.teamify.service;

import lombok.RequiredArgsConstructor;
import org.original.name.teamify.dto.CreateTeamRequest;
import org.original.name.teamify.exception.TeamifyException;
import org.original.name.teamify.model.Team;
import org.original.name.teamify.model.User;
import org.original.name.teamify.repository.TeamRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;
    private final IdentityService identityService;
    private final UserService userService;

    public Team getById(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new TeamifyException("Team with id " + id + " does not exists"));
    }

    public Team createTeam(CreateTeamRequest createTeamRequest, String token) {
        User leader = identityService.currentUser(token);
        if (teamRepository.existsByName(createTeamRequest.getName())) {
            throw new TeamifyException("Team with name " + createTeamRequest.getName() + " already exists");
        }
        Team team = new Team();

        BeanUtils.copyProperties(createTeamRequest, team);
        team.setLeader(leader);

        teamRepository.save(team);
        return team;
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public List<Team> getByLeaderId(Long leaderId) {
        User leader = userService.getUser(leaderId);
        return teamRepository.findAllByLeader(leader);
    }
}
