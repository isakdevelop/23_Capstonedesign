package com.repair.repair.dto.response.user;

import lombok.Getter;

@Getter
public class UserDeleteResponseDto {
    private int state;
    private String message;

    public UserDeleteResponseDto(int state, String message) {
        this.state = state;
        this.message = message;
    }
}
