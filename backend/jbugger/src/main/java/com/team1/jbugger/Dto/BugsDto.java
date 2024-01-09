package com.team1.jbugger.Dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.team1.jbugger.Enums.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BugsDto {
    private int idBug;
    private String title;
    private String description;
    private String version;
    private LocalDate targetDate;;
    private BugStatus status;
    private String fixedVersion;
    private BugSeverity severity;
}
