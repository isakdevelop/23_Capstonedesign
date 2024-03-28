package com.repair.api.dto.request.user;

import lombok.Getter;

@Getter
public class UserUpdateRequestDto {
    private Long userId;
    private String name;
    private String phone;
    private String password;
}
