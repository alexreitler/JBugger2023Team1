package com.team1.jbugger.Bug_Calls;

import com.team1.jbugger.Entity.*;
import com.team1.jbugger.Enums.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BugAttributes {
    int idBug;
    String title;
    String description;
    String version;
    LocalDate targetDate;;
    BugStatus status;
    String fixedVersion;
    BugSeverity severity;
    String CreatedByUsername;
    String AssignedToUsername;


    public static BugAttributes fromBug(Bugs bug) {
        return BugAttributes.builder()
                .idBug(bug.getIdBug())
                .title(bug.getTitle())
                .description(bug.getDescription())
                .version(bug.getVersion())
                .fixedVersion(bug.getFixedVersion())
                .targetDate(bug.getTargetDate())
                .status(bug.getStatus())
                .severity(bug.getSeverity())
                .CreatedByUsername(bug.getUserCreated().getUsername())
                .AssignedToUsername(bug.getUserAssigned().getUsername())
                .build();
    }
}