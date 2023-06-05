package org.original.name.teamify.service;

import lombok.RequiredArgsConstructor;
import org.original.name.teamify.dto.CreateApplicationRequest;
import org.original.name.teamify.exception.BadRequestExcepton;
import org.original.name.teamify.exception.ResourceNotFoundException;
import org.original.name.teamify.exception.RestrictedResourceException;
import org.original.name.teamify.model.Application;
import org.original.name.teamify.model.User;
import org.original.name.teamify.repository.ApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.original.name.teamify.model.Application.ApplicationStatus.*;

@Service
@RequiredArgsConstructor
public class ApplicationService {
    private final TeamService teamService;
    private final IdentityService identityService;
    private final ApplicationRepository repository;

    public Application createApplication(CreateApplicationRequest request, String userToken) {
        var applicant = identityService.currentUser(userToken);
        var team = teamService.getById(request.getTeamId());
        if (team.getLeader().getId().equals(applicant.getId())) {
            throw new BadRequestExcepton("Leader cannot create an application to its own team");
        }
        var application = Application.builder()
                .applicant(applicant)
                .team(team)
                .status(NEW)
                .build();
        repository.save(application);
        return application;
    }

    public Application getApplication(Long applicationId, String userToken) {
        var currentUser = identityService.currentUser(userToken);
        var application = repository.findById(applicationId).orElseThrow(ResourceNotFoundException::new);
        if (hasAccessToApplication(currentUser, application)) {
            return application;
        }
        throw new RestrictedResourceException();
    }

    private boolean hasAccessToApplication(User currentUser, Application application) {
        var currentUserId = currentUser.getId();
        var applicantId = application.getApplicant().getId();
        var leaderId = application.getTeam().getLeader().getId();
        return currentUserId.equals(applicantId) || currentUserId.equals(leaderId);
    }

    public List<Application> getAssignedApplication(String userToken) {
        var leader = identityService.currentUser(userToken);
        var userOwnedTeams = teamService.getByLeaderId(leader.getId());
        return repository.findAllByTeamIn(userOwnedTeams);
    }

    public List<Application> getSubmittedApplications(String userToken) {
        var user = identityService.currentUser(userToken);
        return repository.findAllByApplicant(user);
    }

    public Application approveApplication(Long applicationId, String userToken) {
        return alterStatus(applicationId, userToken, APPROVED);
    }

    public Application rejectApplication(Long applicationId, String userToken) {
        return alterStatus(applicationId, userToken, REJECTED);
    }

    private Application alterStatus(Long applicationId, String userToken, Application.ApplicationStatus targetStatus) {
        var leader = identityService.currentUser(userToken);
        var application = repository.findById(applicationId).orElseThrow(ResourceNotFoundException::new);
        if (!application.getTeam().getLeader().getId().equals(leader.getId())) {
            throw new RestrictedResourceException();
        }
        application.setStatus(targetStatus);
        repository.save(application);
        return application;
    }

    public Application deleteApplication(Long applicationId, String userToken) {
       var application = repository.findById(applicationId).orElseThrow(ResourceNotFoundException::new);
       var currentUser = identityService.currentUser(userToken);
       if(! application.getApplicant().getId().equals(currentUser.getId())){
           throw new RestrictedResourceException();
       }
       repository.delete(application);
       return application;
    }
}
