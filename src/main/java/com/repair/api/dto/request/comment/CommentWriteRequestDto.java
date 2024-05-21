package com.repair.api.dto.request.comment;

import lombok.Getter;

@Getter
public class CommentWriteRequestDto {
    private Long userId;
    private Long boardId;
    private String content;
    private String name;
}
