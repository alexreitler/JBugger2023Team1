package com.team1.jbugger.Mapper;

import com.team1.jbugger.Dto.AttachementsDto;
import com.team1.jbugger.Entity.Attachments;

public class AttachementsMapper {
    public static AttachementsDto mapToAttachementsDto(Attachments attachment)
    {
        return new AttachementsDto(
                attachment.getIdAtt(),
                attachment.getAttContent()
        );
    }
    public static Attachments mapToAttachements(AttachementsDto attachementsDto)
    {
        return new Attachments(
                attachementsDto.getIdAtt(),
                attachementsDto.getAttContent()
        );
    }
}
