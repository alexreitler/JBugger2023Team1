package com.team1.jbugger.Bug_Calls;

import com.team1.jbugger.Enums.BugSeverity;
import com.team1.jbugger.Enums.BugStatus;
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
public class BugUpdateRequest {
    String title;
    String description;
    String version;
    LocalDate targetDate;;
    BugStatus status;
    String fixedVersion;
    BugSeverity severity;
    String AssignedToUsername;
}