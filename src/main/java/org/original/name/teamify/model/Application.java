package org.original.name.teamify.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Application {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private User applicant;
    @ManyToOne
    private Team team;
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    public enum ApplicationStatus {
        NEW, APPROVED, REJECTED, CANCELLED
    }

}
