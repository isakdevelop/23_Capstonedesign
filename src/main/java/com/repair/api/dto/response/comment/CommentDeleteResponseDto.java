package com.repair.api.dto.response.comment;

import lombok.Getter;

@Getter
public class CommentDeleteResponseDto {
    private int status;
    private String message;

    public CommentDeleteResponseDto(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
