package com.team1.jbugger.Bug_Calls;

import com.team1.jbugger.Entity.Comments;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContentComment {
    private int idComment;
    private String text;
    private LocalDate date;
    String author;

    public static ContentComment fromComment(Comments comment) {
        return ContentComment.builder()
                .idComment(comment.getIdComment())
                .text(comment.getText())
                .date(comment.getDate())
                .author(comment.getUser().getUsername())
                .build();
    }
}
