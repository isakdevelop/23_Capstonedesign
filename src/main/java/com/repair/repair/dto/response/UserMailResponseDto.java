package com.repair.repair.dto.response;

import lombok.Getter;

@Getter
public class UserMailResponseDto {
    private int state;
    private String message;

    public UserMailResponseDto(int state, String message) {
        this.state = state;
        this.message = message;
    }
}
