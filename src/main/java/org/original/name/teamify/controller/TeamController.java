package org.original.name.teamify.controller;

import lombok.RequiredArgsConstructor;
import org.original.name.teamify.dto.CreateTeamRequest;
import org.original.name.teamify.dto.TeamDto;
import org.original.name.teamify.service.TeamService;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping
    public TeamDto createTeam(@RequestBody CreateTeamRequest createTeamRequest){
        return TeamDto.ofTeam(teamService.createTeam(createTeamRequest));
    }
}
