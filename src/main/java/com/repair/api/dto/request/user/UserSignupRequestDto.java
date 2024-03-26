package com.repair.api.dto.request.user;

import lombok.Getter;

@Getter
public class UserSignupRequestDto {
    private String email;
    private String password;
    private String name;
    private String phone;
}
