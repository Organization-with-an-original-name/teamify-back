package org.original.name.teamify.controller;

import lombok.RequiredArgsConstructor;
import org.original.name.teamify.dto.ApplicationDto;
import org.original.name.teamify.dto.CreateApplicationRequest;
import org.original.name.teamify.model.Application;
import org.original.name.teamify.service.ApplicationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/application")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ApplicationController {
    private final ApplicationService applicationService;

    @PostMapping
    public ApplicationDto createApplication(@RequestBody CreateApplicationRequest request,
                                            @RequestHeader("Access-token") String token) {
        return ApplicationDto.fromApplication(applicationService.createApplication(request, token));
    }

    @GetMapping("/{id}")
    private ApplicationDto getApplication(@PathVariable("id") Long applicationId,
                                          @RequestHeader("Access-token") String token
    ) {
        return ApplicationDto.fromApplication(applicationService.getApplication(applicationId, token));
    }

    @GetMapping("/assigned")
    private List<ApplicationDto> getAssignedApplications(@RequestHeader("Access-token") String token) {
        return applicationService.getAssignedApplication(token).stream()
                .map(ApplicationDto::fromApplication)
                .toList();
    }

    @GetMapping("/submitted")
    private List<ApplicationDto> getSubmittedApplications(@RequestHeader("Access-token") String token) {
        return applicationService.getSubmittedApplications(token).stream()
                .map(ApplicationDto::fromApplication)
                .toList();
    }

    @PatchMapping("/{id}/approve")
    private ApplicationDto approve(@PathVariable("id") Long applicationId,
                                   @RequestHeader("Access-token") String userToken) {
        return ApplicationDto.fromApplication(applicationService.approveApplication(applicationId, userToken));
    }

    @PatchMapping("/{id}/reject")
    private ApplicationDto reject(@PathVariable("id") Long applicationId,
                                  @RequestHeader("Access-token") String userToken) {
        return ApplicationDto.fromApplication(applicationService.rejectApplication(applicationId, userToken));
    }

    @DeleteMapping("/{id}")
    private ApplicationDto delete(@PathVariable("id") Long applicationId,
                                  @RequestHeader("Access-token") String userToken) {
        return ApplicationDto.fromApplication(applicationService.deleteApplication(applicationId, userToken));
    }

}
