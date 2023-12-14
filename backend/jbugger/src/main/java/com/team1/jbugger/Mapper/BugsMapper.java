package com.team1.jbugger.Mapper;

import com.team1.jbugger.Dto.BugsDto;
import com.team1.jbugger.Entity.Bugs;

public class BugsMapper {
    public static BugsDto mapToBugsDto(Bugs bug)
    {
        return new BugsDto(
                bug.getIdBug(),
                bug.getTitle(),
                bug.getDescription(),
                bug.getVersion(),
                bug.getTargetDate(),
                bug.getStatus(),
                bug.getFixedVersion(),
                bug.getSeverity()
        );
    }
    public static Bugs mapToBugs(BugsDto bugsDto)
    {
        return new Bugs(
                bugsDto.getIdBug(),
                bugsDto.getTitle(),
                bugsDto.getDescription(),
                bugsDto.getVersion(),
                bugsDto.getTargetDate(),
                bugsDto.getStatus(),
                bugsDto.getFixedVersion(),
                bugsDto.getSeverity()
        );
    }
}
