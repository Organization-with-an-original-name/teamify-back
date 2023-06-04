package org.original.name.teamify.controller;

import lombok.RequiredArgsConstructor;
import org.original.name.teamify.dto.CreateTeamRequest;
import org.original.name.teamify.dto.TeamDto;
import org.original.name.teamify.service.TeamService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/team")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TeamController {
    private final TeamService teamService;

    @GetMapping("/{id}")
    public TeamDto getTeam(@PathVariable Long id){
        return TeamDto.ofTeam(teamService.getById(id));
    }

    @GetMapping
    public List<TeamDto> getAllTeams(){
       return teamService.getAllTeams().stream()
               .map(TeamDto::ofTeam)
               .toList();
    }

    @GetMapping(params = {"leaderId"})
    public List<TeamDto> getAllByLeader(@RequestParam Long leaderId){
        return teamService.getByLeaderId(leaderId).stream()
                .map(TeamDto::ofTeam)
                .toList();
    }

    @PostMapping
    public TeamDto createTeam(@RequestBody CreateTeamRequest createTeamRequest, @RequestHeader("Access-token")String token){
        return TeamDto.ofTeam(teamService.createTeam(createTeamRequest, token));
    }
}
