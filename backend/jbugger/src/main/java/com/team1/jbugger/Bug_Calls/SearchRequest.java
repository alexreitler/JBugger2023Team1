package com.team1.jbugger.Bug_Calls;

import com.team1.jbugger.Enums.BugSeverity;
import com.team1.jbugger.Enums.BugStatus;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchRequest {
    int pageNumber;
    int pageSize;
    String title;
    String description;
    String version;
    LocalDate targetDate;;
    BugStatus status;
    String fixedVersion;
    BugSeverity severity;
    String CreatedByUsername;
    String AssignedToUsername;
}