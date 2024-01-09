package com.team1.jbugger.Mapper;

import com.team1.jbugger.Dto.AttachmentsDto;
import com.team1.jbugger.Entity.Attachments;

public class AttachmentsMapper {
    public static AttachmentsDto mapToAttachmentsDto(Attachments attachment)
    {
        return new AttachmentsDto(
                attachment.getIdAtt(),
                attachment.getAttContent()
        );
    }
    public static Attachments mapToAttachments(AttachmentsDto attachmentsDto)
    {
        return new Attachments(
                attachmentsDto.getIdAtt(),
                attachmentsDto.getAttContent()
        );
    }
}
