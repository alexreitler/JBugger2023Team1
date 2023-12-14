package com.team1.jbugger.Mapper;

import com.team1.jbugger.Dto.HistoryDto;
import com.team1.jbugger.Entity.History;

public class HistoryMapper {
    public static HistoryDto mapToHistoryDto(History history)
    {
        return new HistoryDto(
                history.getIdHistory(),
                history.getModifiedDate(),
                history.getAfterStatus(),
                history.getBeforeStatus(),
                history.getModifiedBy()
        );
    }
    public static History mapToHistory(HistoryDto historyDto)
    {
        return new History(
                historyDto.getIdHistory(),
                historyDto.getModifiedDate(),
                historyDto.getAfterStatus(),
                historyDto.getBeforeStatus(),
                historyDto.getModifiedBy()
        );
    }
}
