package com.repair.api.common;

import lombok.Getter;

@Getter
public class ResultResponseDto {
    private int status;
    private String message;

    public ResultResponseDto(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
