package com.repair.api.dto.request.user;

import lombok.Getter;

@Getter
public class UserLoginRequestDto {
    private String email;
    private String password;
}
