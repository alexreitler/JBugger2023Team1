package com.team1.jbugger.Bug_Calls;

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
public class BugAddRequest {
    private String title;
    private String description;
    private String version;
    private LocalDate targetDate;;
    private BugStatus status;
    private String fixedVersion;
    private BugSeverity severity;
    String AssignedToUsername;
    byte[] attachmentContent;
}