package com.team1.jbugger.Events;

import com.team1.jbugger.Entity.Bugs;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
@Builder
public class BugFinished {
    private Bugs closedBug;
}

