package com.repair.api.dto.response.user;

import lombok.Getter;

@Getter
public class UserLoginResponseDto {
    private String id;
    private String email;
    private String name;
    private Integer type;

    public UserLoginResponseDto(String id, String email, String name, int type) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.type = type;
    }
}
