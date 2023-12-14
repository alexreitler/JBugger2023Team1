package com.team1.jbugger.Mapper;

import com.team1.jbugger.Dto.BugsDto;
import com.team1.jbugger.Dto.CommentsDto;
import com.team1.jbugger.Entity.Bugs;
import com.team1.jbugger.Entity.Comments;

public class CommentsMapper {
    public static CommentsDto mapToCommentsDto(Comments comment)
    {
        return new CommentsDto(
                comment.getIdComment(),
                comment.getText(),
                comment.getDate()
        );
    }
    public static Comments mapToComments(CommentsDto commentsDto)
    {
        return new Comments(
                commentsDto.getIdComment(),
                commentsDto.getText(),
                commentsDto.getDate()
        );
    }
}
