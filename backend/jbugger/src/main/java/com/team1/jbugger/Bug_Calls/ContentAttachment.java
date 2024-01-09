package com.team1.jbugger.Bug_Calls;

import com.team1.jbugger.Entity.Attachments;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Base64;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContentAttachment {
    int idAtt;
    byte[] attContent;

    public static ContentAttachment fromAttachment(Attachments attachment) {

        byte[] attCont = Base64.getDecoder().decode(attachment.getAttContent());

        return ContentAttachment.builder()
                .idAtt(attachment.getIdAtt())
                .attContent(attCont)
                .build();
    }
}