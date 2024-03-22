package com.repair.repair.dto.request.user;

import lombok.Getter;

@Getter
public class UserSignupRequestDto {
    private String email;
    private String password;
    private String name;
    private String phone;
}
