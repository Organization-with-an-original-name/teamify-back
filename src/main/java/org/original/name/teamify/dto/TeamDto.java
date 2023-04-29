package org.original.name.teamify.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.original.name.teamify.model.Team;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
public class TeamDto {
    public static TeamDto ofTeam(Team team) {
        TeamDto teamDto = new TeamDto();
        BeanUtils.copyProperties(team, teamDto);
        teamDto.setLeaderId(team.getLeader().getId());
        return teamDto;
    }

    Long id;
    Long leaderId;
    String name;
    String description;

}
