package com.team1.jbugger.Bug_Calls;

import com.team1.jbugger.Entity.History;
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
public class ContentHistory {
    private int idHistory;
    private LocalDate modifiedDate;
    private BugStatus afterStatus;
    private BugStatus beforeStatus;

    public static ContentHistory fromHistory(History history) {
        return ContentHistory.builder()
                .idHistory(history.getIdHistory())
                .modifiedDate(history.getModifiedDate())
                .beforeStatus(history.getBeforeStatus())
                .afterStatus(history.getAfterStatus())
                .build();
    }
}