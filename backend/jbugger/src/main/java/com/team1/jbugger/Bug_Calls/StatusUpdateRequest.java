package com.team1.jbugger.Bug_Calls;

import com.team1.jbugger.Enums.BugStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatusUpdateRequest {
    BugStatus status;
}