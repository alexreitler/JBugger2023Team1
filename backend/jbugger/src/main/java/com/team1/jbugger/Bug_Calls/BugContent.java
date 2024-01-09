package com.team1.jbugger.Bug_Calls;

import com.team1.jbugger.Entity.Bugs;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BugContent {
    BugAttributes bugAttributes;
    List<ContentComment> comments;
    List<ContentAttachment> attachments;
    List<ContentHistory> history;

    public static BugContent fromBug(Bugs bug) {

        List<ContentComment> comments = bug.getComments().stream().map(ContentComment::fromComment).toList();
        List<ContentAttachment> attachments = bug.getAttachments().stream().map(ContentAttachment::fromAttachment).toList();
        List<ContentHistory> historyList = bug.getHistory().stream().map(ContentHistory::fromHistory).toList();

        return BugContent.builder()
                .bugAttributes(BugAttributes.fromBug(bug))
                .comments(comments)
                .attachments(attachments)
                .history(historyList)
                .build();
    }
}