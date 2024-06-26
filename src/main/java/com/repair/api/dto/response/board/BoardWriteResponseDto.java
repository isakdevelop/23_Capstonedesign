package com.repair.api.dto.response.board;

import lombok.Getter;

@Getter
public class BoardWriteResponseDto {
    private int status;
    private String message;

    public BoardWriteResponseDto(int status, String message) {
        this.status = status;
        this.message = message;
    }
}