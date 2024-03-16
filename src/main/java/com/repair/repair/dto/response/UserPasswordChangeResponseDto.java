package com.repair.repair.dto.response;

import lombok.Getter;

@Getter
public class UserPasswordChangeResponseDto {
    private int state;
    private String message;

    public UserPasswordChangeResponseDto(int state, String message) {
        this.state = state;
        this.message = message;
    }
}
