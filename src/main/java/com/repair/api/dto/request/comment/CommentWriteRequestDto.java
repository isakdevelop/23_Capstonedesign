package com.repair.api.dto.request.comment;

import lombok.Getter;

@Getter
public class CommentWriteRequestDto {
    private String userId;
    private String boardId;
    private String commentId;
    private String content;
    private String name;
}
