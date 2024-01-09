package com.team1.jbugger.Dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import com.team1.jbugger.Enums.BugStatus;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistoryDto {
    private int idHistory;
    private LocalDate modifiedDate;
    private BugStatus afterStatus;
    private BugStatus beforeStatus;
    private String modifiedBy;
}
