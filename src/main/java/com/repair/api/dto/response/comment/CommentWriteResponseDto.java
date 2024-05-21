package com.repair.api.dto.response.comment;

import lombok.Getter;

@Getter
public class CommentWriteResponseDto {
    private int status;
    private String message;

    public CommentWriteResponseDto(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
