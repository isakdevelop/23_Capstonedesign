package com.repair.api.dto.response.user;

import lombok.Getter;

@Getter
public class UserUpdateResponseDto {
    private int state;
    private String message;

    public UserUpdateResponseDto(int state, String message) {
        this.state = state;
        this.message = message;
    }
}
