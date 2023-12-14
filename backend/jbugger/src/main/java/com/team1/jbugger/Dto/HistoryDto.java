package com.team1.jbugger.Dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
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
    private String afterStatus;
    private String beforeStatus;
    private String modifiedBy;
}
