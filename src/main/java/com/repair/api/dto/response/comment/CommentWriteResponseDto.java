package com.repair.api.dto.response.comment;

import lombok.Getter;

@Getter
public class CommentWriteResponseDto {
    private Long id;
    private String message;

    public CommentWriteResponseDto(Long id, String message) {
        this.id = id;
        this.message = message;
    }
}
