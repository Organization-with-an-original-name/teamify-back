package org.original.name.teamify.dto;

import lombok.Data;
import org.original.name.teamify.model.Application;

@Data
public class ApplicationDto {
    private Long id;
    private Long applicantId;
    private Long teamId;
    private String status;

    public static ApplicationDto fromApplication(Application application){
        var dto = new ApplicationDto();
        dto.setId(application.getId());
        dto.setApplicantId(application.getApplicant().getId());
        dto.setTeamId(application.getTeam().getId());
        dto.setStatus(application.getStatus().name());
        return dto;
    }
}
