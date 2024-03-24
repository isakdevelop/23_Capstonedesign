package com.repair.repair.dto.response.comment;

import lombok.Getter;

@Getter
public class CommentWriteResponseDto {
    private String id;
    private String message;

    public CommentWriteResponseDto(String id, String message) {
        this.id = id;
        this.message = message;
    }
}
