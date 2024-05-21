package com.repair.api.dto.response.board;

import lombok.Getter;

@Getter
public class BoardDeleteResponseDto {
    private int status;
    private String message;

    public BoardDeleteResponseDto(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
